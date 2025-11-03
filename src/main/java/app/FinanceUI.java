package app;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class FinanceUI extends JFrame {
    DefaultTableModel model = new DefaultTableModel(new String[]{"ID", "Name", "Amount"}, 0);
    JTable table = new JTable(model);

    public FinanceUI() {
        setTitle("Personal Finance Manager");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JButton addBtn = new JButton("Add Transaction");
        JButton refreshBtn = new JButton("Refresh");

        JPanel panel = new JPanel();
        panel.add(addBtn);
        panel.add(refreshBtn);

        add(new JScrollPane(table), BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);

        addBtn.addActionListener(e -> addTransaction());
        refreshBtn.addActionListener(e -> loadData());

        loadData();
        setVisible(true);
    }

    void addTransaction() {
        String name = JOptionPane.showInputDialog("Enter name:");
        String amtStr = JOptionPane.showInputDialog("Enter amount:");
        try {
            double amt = Double.parseDouble(amtStr);
            TransactionDAO.insert(name, amt);
            loadData();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid input");
        }
    }

    void loadData() {
        model.setRowCount(0);
        List<Transaction> list = TransactionDAO.getAll();
        for (Transaction t : list) {
            model.addRow(new Object[]{t.id, t.name, t.amount});
        }
    }
}
