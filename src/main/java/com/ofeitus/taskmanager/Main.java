package com.ofeitus.taskmanager;

import com.ofeitus.taskmanager.manager.EmployeesManager;
import com.ofeitus.taskmanager.manager.PlanManager;
import com.ofeitus.taskmanager.model.Employee;
import com.ofeitus.taskmanager.ui.renderer.MonthComboBoxRenderer;
import com.ofeitus.taskmanager.ui.table.PlanTable;
import com.ofeitus.taskmanager.ui.table.TasksTable;
import com.ofeitus.taskmanager.util.Translator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.Month;

@SpringBootApplication
public class Main extends JFrame {
    private final Logger logger = LogManager.getLogger(Main.class);

    private JComboBox<Employee> employeeComboBox;
    private JComboBox<Month> monthComboBox;

    private final TasksTable tasksTable;
    private final PlanTable planTable;

    private final EmployeesManager employeesManager;
    private final PlanManager planManager;

    public Main(EmployeesManager employeesManager, PlanManager planManager, TasksTable tasksTable, PlanTable planTable) {
        this.employeesManager = employeesManager;
        this.planManager = planManager;
        this.tasksTable = tasksTable;
        this.planTable = planTable;

        setTitle("Application");
        setSize(800, 325);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        initComponents();
        initEventHandling();

        monthComboBox.setSelectedItem(Month.JANUARY);
        employeeComboBox.setSelectedItem(employeeComboBox.getSelectedItem());
    }

    private void initComponents() {
        JPanel employeePicker = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel employeeLabel = new JLabel(Translator.getString("employee"));
        employeeComboBox = new JComboBox<>(employeesManager.getManagedEmployees().toArray(new Employee[0]));
        employeePicker.add(employeeLabel);
        employeePicker.add(employeeComboBox);

        JPanel planTab = new JPanel(new BorderLayout());
        JPanel monthPicker = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel monthLabel = new JLabel(Translator.getString("month"));
        monthComboBox = new JComboBox<>(Month.values());
        monthComboBox.setRenderer(new MonthComboBoxRenderer());
        monthPicker.add(monthLabel);
        monthPicker.add(monthComboBox);
        planTab.add(monthPicker, BorderLayout.NORTH);
        JScrollPane planTableScrollPane = new JScrollPane(planTable);
        planTab.add(planTableScrollPane, BorderLayout.CENTER);

        JTabbedPane tabsPanel = new JTabbedPane();
        JScrollPane tasksTableScrollPane = new JScrollPane(tasksTable);
        tabsPanel.add(Translator.getString("tasks"), tasksTableScrollPane);
        tabsPanel.add(Translator.getString("plan"), planTab);

        Container container = getContentPane();
        container.add(employeePicker, BorderLayout.NORTH);
        container.add(tabsPanel, BorderLayout.CENTER);
    }

    private void initEventHandling() {
        employeeComboBox.addActionListener(new EmployeeChangeAction());
        monthComboBox.addActionListener(new MonthChangeAction());
    }

    class EmployeeChangeAction extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            employeesManager.setSelectedEmployee((Employee) employeeComboBox.getSelectedItem());
            planManager.updateTasksByEmployee(employeesManager.getSelectedEmployee());
            tasksTable.update();
            planTable.update();
            logger.info("Selected employee: " + employeesManager.getSelectedEmployee());
        }
    }

    class MonthChangeAction extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            planManager.setSelectedMonth((Month) monthComboBox.getSelectedItem());
            planTable.update();
            logger.info("Selected month: " + monthComboBox.getSelectedItem());
        }
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(Main.class).headless(false).run(args);

        EventQueue.invokeLater(() -> {
            Main frame = context.getBean(Main.class);
            frame.setVisible(true);
        });
    }
}
