import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class gui {
    public static void main(String[] args) {

        JFrame frame = new JFrame();
        frame.setSize(700,500);
        frame.setTitle("Choice Roller");
        frame.getContentPane().setBackground(new Color(24,110,65));

        JButton rollButton = new JButton("ROLL!");
        JButton addButton = new JButton("Add Item");
        JButton removeButton = new JButton("Remove Item");
        JTextField hobbyField = new JTextField();
        JLabel resultLabel = new JLabel("Roll the Die!");

        ImageIcon diceImage = new ImageIcon("images/die1.png");
        Image scaledImage = diceImage.getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH);
        diceImage = new ImageIcon(scaledImage);

        JLabel diceLabel = new JLabel(diceImage);
        JTextArea hobbyList = new JTextArea();

        ArrayList<String> activities = new ArrayList<>();

        resultLabel.setForeground(new Color(212, 175, 55));
        resultLabel.setFont(new Font ("Arial", Font.BOLD, 20));


        for (int i = 0; i < activities.size(); i++) {
            hobbyList.append((i + 1 + ".") + activities.get(i) + "\n");
        }

        frame.setLayout(null);
        rollButton.setBounds( 250,350,200,50);
        addButton.setBounds(460, 300, 100, 40);
        removeButton.setBounds(575, 300, 100, 40 );
        resultLabel.setBounds(290, 250, 400, 50);
        diceLabel.setBounds(325, 100, 100, 100);
        hobbyField.setBounds(250, 300, 200, 40);
        hobbyList.setBounds(25,95,180,325);

        frame.add(rollButton);
        frame.add(resultLabel);
        frame.add(diceLabel);
        frame.add(hobbyField);
        frame.add(addButton);
        frame.add(removeButton);
        frame.add(hobbyList);
        rollButton.addActionListener(e -> {
            if (activities.isEmpty()) {
                resultLabel.setText("Add an item first!");
                return;
            }
            if (activities.size() == 1) {
                resultLabel.setText("Add another item");
                return;
            }
            int dieSize = 6;
            if (activities.size()<= 4) {
                dieSize = 4;
            }
            else if (activities.size()<=6){
                dieSize = 6;
            }
            else if (activities.size()<=8){
                dieSize = 8;
            }
            else if (activities.size()<=10) {
                dieSize = 10;
            }
            else if (activities.size()<=12) {
                dieSize = 12;
            }
            else {
                dieSize = 20;
            }
            int roll = (int)(Math.random()* dieSize) +1 ;
            while (roll > activities.size()) {
                roll = (int) (Math.random() * dieSize) + 1;
            }

            ImageIcon rolledDice =new ImageIcon("images/d" + dieSize + "_roll" + roll + ".png");
            Image scaledDice = rolledDice.getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH);
            diceLabel.setIcon(new ImageIcon(scaledDice));

            resultLabel.setText("You Rolled: " + roll + " - " + activities.get(roll-1));
        });
        addButton.addActionListener(e -> {
            String newHobby = hobbyField.getText();
            if (!newHobby.trim().isEmpty() && activities.size() < 20 ) {
                activities.add(newHobby);
                hobbyList.append(activities.size() + "." + newHobby + "\n");
                hobbyField.setText("");
            }
            else if (newHobby.trim().isEmpty()) {
                resultLabel.setText("Add an item!");
            }
            else {
                resultLabel.setText("Maximum 20 items!");
            }
        });
        removeButton.addActionListener( b -> {
            String removeText = hobbyField.getText();
           try {
               int removeNumber = Integer.parseInt(removeText);
               if (removeNumber < 1 || removeNumber > activities.size()) {
                   resultLabel.setText("Choose a valid item number!");
                   return;
               }
               activities.remove(removeNumber - 1);
           }
           catch (NumberFormatException ex) {
               activities.remove(removeText);
           }
           hobbyField.setText("");
            hobbyList.setText("");
            for (int i = 0; i < activities.size(); i++) {
                hobbyList.append((i + 1) + "." + activities.get(i) + "\n");
            }
;        });
        hobbyField.addActionListener(a -> {
                    addButton.doClick();
                });
        frame.setVisible(true);
    }

}
