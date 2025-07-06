import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LaunchFrame extends JFrame implements ActionListener {

    private JComboBox<String> algorithmInput;
    private JTextField arraySizeInput;
    private JComboBox<String> initialOrderInput;
    private JTextField scaleInput;
    private JTextField speedInput;
    private JButton button;

    private Font jetBrains=new Font("JetBrains Mono", Font.PLAIN, 15);

    LaunchFrame() {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("DS-VIZ");
        this.setSize(500, 400);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(new Color(220,180,240));

        ImageIcon image = new ImageIcon("project logo.png");
        this.setIconImage(image.getImage());

        var gbl = new GridBagLayout();
        var gbc = new GridBagConstraints();
        this.setLayout(gbl);

        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 0;
        gbc.ipady = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.weighty = 0;
        gbc.gridx = 0;

        JLabel heading=new JLabel("Sorting Visualizer");//text and fonts on screen
        heading.setFont(new Font("Verdana", Font.BOLD, 25));
        this.add(heading, gbc);

        gbc.gridy = 1;

        var label = new MyLabel("Select algorithm:");
        gbl.setConstraints(label, gbc);
        this.add(label);
        gbc.gridy++;

        label = new MyLabel("Array size:");
        gbl.setConstraints(label, gbc);
        this.add(label);
        gbc.gridy++;

        label = new MyLabel("Initial order:");
        gbl.setConstraints(label, gbc);
        this.add(label);
        gbc.gridy++;

        label = new MyLabel("Visual scale:");
        gbl.setConstraints(label, gbc);
        this.add(label);
        gbc.gridy++;

        label = new MyLabel("Running Speed:");
        gbl.setConstraints(label, gbc);
        this.add(label);
        gbc.gridy++;

        // Second Column;
        gbc.gridx = 1;
        gbc.weightx = 2;
        gbc.gridy = 1;

        // Drop-down selector for sort algorithm
        algorithmInput = new JComboBox<>();
        algorithmInput.addItem("Bubble Sort");
        algorithmInput.addItem("Insertion Sort");
        algorithmInput.addItem("Selection Sort");
        algorithmInput.addItem("Merge Sort");
        gbl.setConstraints(algorithmInput, gbc);
        algorithmInput.setPreferredSize(new Dimension(100, 30));
        algorithmInput.setFont(jetBrains);
        algorithmInput.setFocusable(false);
        this.add(algorithmInput);
        gbc.gridy++;

        // Text field for array size
        arraySizeInput = new JTextField("10");
        arraySizeInput.addActionListener(this);
        gbl.setConstraints(arraySizeInput, gbc);
        arraySizeInput.setPreferredSize(new Dimension(100, 30));
        arraySizeInput.setFont(jetBrains);
        this.add(arraySizeInput);
        gbc.gridy++;

        // Drop-down selector for initial order
        initialOrderInput = new JComboBox<>();
        initialOrderInput.addItem("Random");
        initialOrderInput.addItem("Ascending");
        initialOrderInput.addItem("Descending");
        initialOrderInput.setSelectedIndex(0);
        gbl.setConstraints(initialOrderInput, gbc);
        initialOrderInput.setPreferredSize(new Dimension(100, 30));
        initialOrderInput.setFont(jetBrains);
        initialOrderInput.setFocusable(false);
        this.add(initialOrderInput);
        gbc.gridy++;

        // Text field for scale
        scaleInput = new JTextField("12");
        scaleInput.addActionListener(this);
        gbl.setConstraints(scaleInput, gbc);
        scaleInput.setPreferredSize(new Dimension(100, 30));
        scaleInput.setFont(jetBrains);
        this.add(scaleInput);
        gbc.gridy++;

        // Text field for speed
        speedInput = new JTextField("2");
        speedInput.addActionListener(this);
        gbl.setConstraints(speedInput, gbc);
        speedInput.setPreferredSize(new Dimension(100, 30));
        speedInput.setFont(jetBrains);
        this.add(speedInput);
        gbc.gridy++;

        // Run Button;
        button = new JButton("Run");
        button.setPreferredSize(new Dimension(100, 35));
        button.setFont(jetBrains.deriveFont(Font.BOLD,20));
        button.addActionListener(this);
        button.setForeground(Color.red);
        button.setBackground(Color.green);
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.weighty = 1;
        gbl.setConstraints(button, gbc);
        this.add(button);
        gbc.gridy++;

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        int size, scale;
        double speed;

        try {
            size = Integer.parseInt(arraySizeInput.getText());
            scale = Integer.parseInt(scaleInput.getText());
            speed = Double.parseDouble(speedInput.getText());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Please fill all fields correctly",
                    "Invalid Input", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (size <= 0 || scale <= 0 || speed <= 0 || Double.isInfinite(speed) || Double.isNaN(speed)) {
            JOptionPane.showMessageDialog(this,
                    "Please fill all fields correctly",
                    "Invalid Input", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int algo=algorithmInput.getSelectedIndex();
        int order=initialOrderInput.getSelectedIndex();

        SortingFrame sortingFrame=new SortingFrame(algo, order, size, scale, speed);
        sortingFrame.start();
    }

    public static void main(String[] args) {
        new LaunchFrame();
    }
}
