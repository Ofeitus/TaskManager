package com.ofeitus.taskmanager;

import com.ofeitus.taskmanager.model.Employee;
import com.ofeitus.taskmanager.model.Month;
import com.ofeitus.taskmanager.model.Task;
import com.ofeitus.taskmanager.service.impl.EmployeeServiceImpl;
import com.ofeitus.taskmanager.service.impl.TaskServiceImpl;
import com.ofeitus.taskmanager.ui.CustomJTableRenderer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.time.YearMonth;
import java.util.*;
import java.util.List;

@SpringBootApplication
public class Main extends JFrame {
    private final Logger logger = LogManager.getLogger(Main.class);

    private JPanel rootPanel;
    private JComboBox<Employee> employeeComboBox;
    private JPanel tablePanel;
    private JLabel employeeLabel;
    private JPanel employeePicker;
    private JTabbedPane tabs;
    private JPanel tasksTab;
    private JPanel planTab;
    private JPanel monthPicker;
    private JLabel monthLabel;
    private JComboBox<Month> monthComboBox;
    private JTable tasksTable;
    private JTable planTable;

    private void updateTaskTable(TaskServiceImpl taskService) {
        Locale locale = Locale.getDefault();
        ResourceBundle rb = ResourceBundle.getBundle("lang/messages", locale);
        if (employeeComboBox.getSelectedItem() == null) {
            return;
        }

        // Get tasks by selected employee
        List<Task> tasks = taskService.getTasksByEmployee(
                (Employee) Objects.requireNonNull(employeeComboBox.getSelectedItem())
        );
        if (tasks.isEmpty()) {
            return;
        }
        // Specify date pattern
        String pattern = "dd.MM.yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Object[][] data = tasks.stream().map(task -> new Object[]{
                task.isCompleted(),
                task.getName(),
                simpleDateFormat.format(task.getStartDate()),
                simpleDateFormat.format(task.getEndDate()),
                simpleDateFormat.format(task.getCompletionDate())
        }).toArray(Object[][]::new);
        String[] columnNames = {
                rb.getString("completed"),
                rb.getString("task"),
                rb.getString("start-date"),
                rb.getString("end-date"),
                rb.getString("completion-date")
        };
        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            // for using JCheckBox in table cell
            @Override
            public Class getColumnClass(int columnIndex) {
                if (columnIndex == 0) {
                    return Boolean.class;
                }
                return super.getColumnClass(columnIndex);
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        tasksTable.setModel(model);
        // Resize first column to content
        tasksTable.getColumnModel().getColumn(0).setMaxWidth(
                tasksTable.getColumnModel().getColumn(0).getPreferredWidth()
        );

        logger.info("Selected employee: " + employeeComboBox.getSelectedItem());
    }

    private void updatePlanTable(TaskServiceImpl taskService) {
        Locale locale = Locale.getDefault();
        ResourceBundle rb = ResourceBundle.getBundle("lang/messages", locale);
        if (employeeComboBox.getSelectedItem() == null) {
            return;
        }

        // Get tasks by selected employee
        List<Task> tasks = taskService.getTasksByEmployee(
                (Employee) Objects.requireNonNull(employeeComboBox.getSelectedItem())
        );
        if (tasks.isEmpty()) {
            return;
        }

        // Count days in selected month
        int selectedMonth = ((Month) Objects.requireNonNull(monthComboBox.getSelectedItem())).ordinal();
        int lengthOfMonth = YearMonth.of(
                Year.now().getValue(),
                selectedMonth + 1
        ).lengthOfMonth();

        // Fill plan
        Object[][] data = new Object[tasks.size()][lengthOfMonth + 1];
        for (int row = 0; row < tasks.size(); row++) {
            Date currentCellDate = new Date(
                    new Date().getYear(),
                    selectedMonth,
                    1
            );
            for (int col = 0; col < lengthOfMonth + 1; col++) {
                if (col == 0) {
                    data[row][col] = tasks.get(row).getName();
                } else {
                    data[row][col] = checkBetween(currentCellDate, tasks.get(row).getStartDate(), tasks.get(row).getEndDate());
                    currentCellDate = new Date(currentCellDate.getTime() + 1000 * 60 * 60 * 24);
                }
            }
        }

        // Set column names
        String[] columnNames = new String[lengthOfMonth + 1];
        columnNames[0] = rb.getString("task");
        for (int col = 1; col < lengthOfMonth + 1; col++) {
            columnNames[col] = String.valueOf(col);
        }

        DefaultTableModel model = new DefaultTableModel(data, columnNames) {

            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        planTable.setModel(model);
        // Resize first column to content
        planTable.getColumnModel().getColumn(0).setMinWidth(130);
        // Use custom renderer to draw different color cells
        planTable.setDefaultRenderer(Object.class, new CustomJTableRenderer());
        planTable.setFocusable(false);
        planTable.setRowSelectionAllowed(false);

        logger.info("Selected month: " + monthComboBox.getSelectedItem());
    }

    private Boolean checkBetween(Date dateToCheck, Date startDate, Date endDate) {
        return dateToCheck.compareTo(startDate) > 0 && dateToCheck.compareTo(endDate) <= 0;
    }

    public Main(EmployeeServiceImpl employeeService, TaskServiceImpl taskService) {
        setTitle("Application");
        setSize(800, 325);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(rootPanel);

        // Get employees and insert to combobox
        DefaultComboBoxModel<Employee> employeeComboBoxModel = new DefaultComboBoxModel<>(
                employeeService.getAllEmployees().toArray(new Employee[0])
        );
        employeeComboBox.setModel(employeeComboBoxModel);

        // Set months combobox
        DefaultComboBoxModel<Month> monthComboBoxModel = new DefaultComboBoxModel<>(
                Month.values()
        );
        monthComboBox.setModel(monthComboBoxModel);

        updateTaskTable(taskService);
        updatePlanTable(taskService);

        // Employee change event
        employeeComboBox.addActionListener(e -> {
            updateTaskTable(taskService);
            updatePlanTable(taskService);
        });

        // Month change event
        monthComboBox.addActionListener(e -> {
            updatePlanTable(taskService);
        });
    }

    public void createUIComponents() {

    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(Main.class).headless(false).run(args);

        EventQueue.invokeLater(() -> {
            Main frame = context.getBean(Main.class);
            frame.setVisible(true);
        });
    }
}
