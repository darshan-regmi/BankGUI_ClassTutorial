import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BankGUI {
    public static void main(String[] args) {
        ArrayList<BankDetails> bankList = new ArrayList<>();

        JFrame frame = new JFrame("Bank");
        frame.setSize(1000, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();
        nameLabel.setBounds(30, 30, 100, 25);
        nameField.setBounds(150, 30, 200, 25);

        JLabel mobileLabel = new JLabel("Mobile:");
        JTextField mobileField = new JTextField();
        mobileLabel.setBounds(30, 70, 100, 25);
        mobileField.setBounds(150, 70, 200, 25);

        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField();
        emailLabel.setBounds(30, 110, 100, 25);
        emailField.setBounds(150, 110, 200, 25);

        JLabel citizenLabel = new JLabel("Citizenship No:");
        JTextField citizenField = new JTextField();
        citizenLabel.setBounds(30, 150, 100, 25);
        citizenField.setBounds(150, 150, 200, 25);

        JLabel balanceLabel = new JLabel("Balance:");
        JTextField balanceField = new JTextField();
        balanceLabel.setBounds(30, 190, 100, 25);
        balanceField.setBounds(150, 190, 200, 25);

        JLabel accountLabel = new JLabel("Account Type:");
        JRadioButton savingBtn = new JRadioButton("Saving");
        JRadioButton currentBtn = new JRadioButton("Current");
        ButtonGroup accGroup = new ButtonGroup();
        accGroup.add(savingBtn);
        accGroup.add(currentBtn);
        accountLabel.setBounds(30, 230, 100, 25);
        savingBtn.setBounds(150, 230, 80, 25);
        currentBtn.setBounds(230, 230, 80, 25);

        JLabel provinceLabel = new JLabel("Province:");
        JComboBox<String> provinceBox = new JComboBox<>(new String[]{
                "Koshi", "Madhesh", "Bagmati", "Gandaki", "Lumbini", "Karnali", "Sudurpashchim"
        });
        provinceLabel.setBounds(30, 270, 100, 25);
        provinceBox.setBounds(150, 270, 200, 25);

        JButton saveBtn = new JButton("Save");
        JButton displayBtn = new JButton("Display");
        JButton clearBtn = new JButton("Clear");

        saveBtn.setBounds(100, 320, 80, 30);
        displayBtn.setBounds(200, 320, 90, 30);
        clearBtn.setBounds(310, 320, 80, 30);

        String[] columns = {"Name", "Mobile", "Email", "Citizenship", "Account Type", "Province", "Balance"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(400, 30, 500, 300);

        frame.add(nameLabel); frame.add(nameField);
        frame.add(mobileLabel); frame.add(mobileField);
        frame.add(emailLabel); frame.add(emailField);
        frame.add(citizenLabel); frame.add(citizenField);
        frame.add(balanceLabel); frame.add(balanceField);
        frame.add(accountLabel); frame.add(savingBtn); frame.add(currentBtn);
        frame.add(provinceLabel); frame.add(provinceBox);
        frame.add(saveBtn); frame.add(displayBtn); frame.add(clearBtn);
        frame.add(scrollPane);

        saveBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText().trim();
                String mobile = mobileField.getText().trim();
                String email = emailField.getText().trim();
                String citizen = citizenField.getText().trim();
                String balanceStr = balanceField.getText().trim();
                String accountType = "";
                if (savingBtn.isSelected()) {
                    accountType = "Saving";
                } else if (currentBtn.isSelected()) {
                    accountType = "Current";
                }
                String province = provinceBox.getSelectedItem().toString();

                if (name.equals("") || mobile.equals("") || email.equals("") || citizen.equals("") || balanceStr.equals("") || accountType.equals("")) {
                    JOptionPane.showMessageDialog(frame, "Please fill in all the fields.");
                    return;
                }

                if (mobile.length() != 10 || !mobile.matches("\\d+")) {
                    JOptionPane.showMessageDialog(frame, "Mobile number must be 10 digits.");
                    return;
                }

                double balance = 0;
                if (balanceStr.matches("\\d+(\\.\\d+)?")) {
                    balance = Double.parseDouble(balanceStr);
                } else {
                    JOptionPane.showMessageDialog(frame, "Balance must be a valid number.");
                    return;
                }

                BankDetails bd = new BankDetails(name, mobile, email, citizen, accountType, province, balance);
                bankList.add(bd);
                JOptionPane.showMessageDialog(frame, "Saved successfully!");
            }
        });

        displayBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.setRowCount(0);
                for (int i = 0; i < bankList.size(); i++) {
                    BankDetails bd = bankList.get(i);
                    model.addRow(new Object[]{
                            bd.name, bd.mobile, bd.email, bd.citizenship,
                            bd.accountType, bd.province, bd.balance
                    });
                }
            }
        });

        clearBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                nameField.setText("");
                mobileField.setText("");
                emailField.setText("");
                citizenField.setText("");
                balanceField.setText("");
                accGroup.clearSelection();
                provinceBox.setSelectedIndex(0);
                model.setRowCount(0);
                bankList.clear();
                JOptionPane.showMessageDialog(frame, "Cleared all data.");
            }
        });
        frame.setVisible(true);
    }
}