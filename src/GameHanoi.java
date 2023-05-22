import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EmptyStackException;

public class GameHanoi extends JFrame {

    private JPanel panelInferior;
    private JPanel panelSuperior;
    private JButton destInitial;
    private JButton destPivot;
    private JButton initDestination;
    private JButton initPivot;
    private JButton pivDestination;
    private JButton buttonInitial;
    private JComboBox discCombo;
    private int step = 0;
    private JButton startButton;
    private boolean isCancelled = false;
    private JButton resetButton;
    private JButton solveButton;
    private JLabel minMovesLabel;
    private JLabel numMovesLabel;
    private boolean shouldContinue = true;
    private JPanel fPanel;
    private JPanel sPanel;
    private JPanel tPanel;
    private JPanel torrePanel;
    private JPanel initialTower;
    private JPanel destinationTower;
    private JPanel pivotTower;
    HanoiManager towerManager = new HanoiManager();


    public GameHanoi() {
        setContentPane(torrePanel);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                initializeGame();
            }
        });

        buttonInitial.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveFromInitialToPivot();
            }
        });

        destInitial.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveFromInitialToDestination();
            }
        });

        initPivot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveFromPivotToInitial();
            }
        });

        destPivot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveFromPivotToDestination();
            }
        });

        initDestination.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveFromDestinationToInitial();
            }
        });

        pivDestination.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveFromDestinationToPivot();
            }
        });

        solveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                solveGame();
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetGame();
            }
        });

        initialTower.setLayout(new BorderLayout());
        fPanel = new JPanel();
        fPanel.setLayout(new BoxLayout(fPanel, BoxLayout.PAGE_AXIS));
        initialTower.add(fPanel, BorderLayout.SOUTH);

        pivotTower.setLayout(new BorderLayout());
        sPanel = new JPanel();
        sPanel.setLayout(new BoxLayout(sPanel, BoxLayout.PAGE_AXIS));
        pivotTower.add(sPanel, BorderLayout.SOUTH);

        destinationTower.setLayout(new BorderLayout());
        tPanel = new JPanel();
        tPanel.setLayout(new BoxLayout(tPanel, BoxLayout.PAGE_AXIS));
        destinationTower.add(tPanel, BorderLayout.SOUTH);
    }
    private void solveGame(){

        if(towerManager.getTowerA().size() != towerManager.getDiscCount()){
            resetMoves();
        }

        if(towerManager.getTowerA().size() == towerManager.getDiscCount()){
            hanoiSolver(towerManager.getDiscCount(), "o", "a", "d");
        }
    }
    private void moveFromInitialToPivot(){
        if (fPanel.getComponentCount() > 0) {
            try {

                if (!towerManager.getTowerB().empty() && towerManager.getTowerB().peek().compareTo(towerManager.getTowerA().peek()) <= 0) {
                    return;
                }
            } catch (EmptyStackException e) {

            }
            Component component = fPanel.getComponent(0);
            if (component instanceof JTextField) {
                towerManager.getTowerB().add(towerManager.getTowerA().pop());

                JTextField field = (JTextField) component;

                JPanel pivotFieldPanel = (JPanel) pivotTower.getComponent(0);

                fPanel.remove(field);
                pivotFieldPanel.add(field, 0);

                fPanel.revalidate();
                fPanel.repaint();

                    pivotFieldPanel.revalidate();
                    pivotFieldPanel.repaint();

                    towerManager.setMovesCount(towerManager.getMovesCount()+1);
                    numMovesLabel.setText(String.valueOf(towerManager.getMovesCount()));

                    recordMove("IP");
                }
            }
        }
        private void moveFromInitialToDestination(){
            if (fPanel.getComponentCount() > 0) {
                try {
                    if (!towerManager.getTowerC().empty() && towerManager.getTowerC().peek().compareTo(towerManager.getTowerA().peek()) <= 0) {
                        return;
                }
            } catch (EmptyStackException e) {

            }
            Component component = fPanel.getComponent(0);
            if (component instanceof JTextField) {
                towerManager.getTowerC().add(towerManager.getTowerA().pop());

                JTextField field = (JTextField) component;

                JPanel pivotFieldPanel = (JPanel) destinationTower.getComponent(0);

                fPanel.remove(field);
                pivotFieldPanel.add(field, 0);

                fPanel.revalidate();
                fPanel.repaint();

                pivotFieldPanel.revalidate();
                pivotFieldPanel.repaint();

                towerManager.setMovesCount(towerManager.getMovesCount()+1);
                numMovesLabel.setText(String.valueOf(towerManager.getMovesCount()));

                recordMove("MOV");
            }
        }
    }
    private void moveFromPivotToInitial(){
        if (sPanel.getComponentCount() > 0) {
            try {
                if (!towerManager.getTowerA().empty() && towerManager.getTowerA().peek().compareTo(towerManager.getTowerB().peek()) <= 0) {
                    return;
                }
            } catch (EmptyStackException e) {
            }
            Component component = sPanel.getComponent(0);
            if (component instanceof JTextField) {
                towerManager.getTowerA().add(towerManager.getTowerB().pop());

                JTextField field = (JTextField) component;

                JPanel initialFieldPanel = (JPanel) initialTower.getComponent(0);

                sPanel.remove(field);
                initialFieldPanel.add(field, 0);

                sPanel.revalidate();
                sPanel.repaint();

                initialFieldPanel.revalidate();
                initialFieldPanel.repaint();

                towerManager.setMovesCount(towerManager.getMovesCount()+1);
                numMovesLabel.setText(String.valueOf(towerManager.getMovesCount()));

                recordMove("PI");

                
            }
        }
    }
    private void moveFromPivotToDestination(){
        if (sPanel.getComponentCount() > 0) {
            try {
                if (!towerManager.getTowerC().empty() && towerManager.getTowerC().peek().compareTo(towerManager.getTowerB().peek()) <= 0) {
                    return;
                }
            } catch (EmptyStackException e) {
            }
            Component component = sPanel.getComponent(0);
            if (component instanceof JTextField) {
                towerManager.getTowerC().add(towerManager.getTowerB().pop());

                JTextField field = (JTextField) component;

                JPanel initialFieldPanel = (JPanel) destinationTower.getComponent(0);

                sPanel.remove(field);
                initialFieldPanel.add(field, 0);

                sPanel.revalidate();
                sPanel.repaint();

                initialFieldPanel.revalidate();
                initialFieldPanel.repaint();

                towerManager.setMovesCount(towerManager.getMovesCount()+1);
                numMovesLabel.setText(String.valueOf(towerManager.getMovesCount()));

                recordMove("PD");

                
            }
        }
    }
    private void moveFromDestinationToInitial(){
        if (tPanel.getComponentCount() > 0) {
            try {
                if (!towerManager.getTowerA().empty() && towerManager.getTowerA().peek().compareTo(towerManager.getTowerC().peek()) <= 0) {
                    return;
                }
            } catch (EmptyStackException e) {
            }
            Component component = tPanel.getComponent(0);
            if (component instanceof JTextField) {
                towerManager.getTowerA().add(towerManager.getTowerC().pop());

                JTextField field = (JTextField) component;

                JPanel initialFieldPanel = (JPanel) initialTower.getComponent(0);

                tPanel.remove(field);
                initialFieldPanel.add(field, 0);

                tPanel.revalidate();
                tPanel.repaint();

                initialFieldPanel.revalidate();
                initialFieldPanel.repaint();

                towerManager.setMovesCount(towerManager.getMovesCount()+1);
                numMovesLabel.setText(String.valueOf(towerManager.getMovesCount()));

                recordMove("DI");

                
            }
        }
    }
    private void moveFromDestinationToPivot(){
        if (tPanel.getComponentCount() > 0) {
            try {
                if (!towerManager.getTowerB().empty() && towerManager.getTowerB().peek().compareTo(towerManager.getTowerC().peek()) <= 0) {
                    return;
                }
            } catch (EmptyStackException e) {

            }
            Component component = tPanel.getComponent(0);
            if (component instanceof JTextField) {
                towerManager.getTowerB().add(towerManager.getTowerC().pop());

                JTextField field = (JTextField) component;

                JPanel initialFieldPanel = (JPanel) pivotTower.getComponent(0);

                tPanel.remove(field);
                initialFieldPanel.add(field, 0);

                tPanel.revalidate();
                tPanel.repaint();

                initialFieldPanel.revalidate();
                initialFieldPanel.repaint();

                towerManager.setMovesCount(towerManager.getMovesCount()+1);
                numMovesLabel.setText(String.valueOf(towerManager.getMovesCount()));

                recordMove("DP");


                
            }
        }
    }
    public void performMove(String  origin, String  destination) {
        if(origin.equals("o") && destination.equals("a")){
            moveFromInitialToPivot();
        }else if(origin.equals("o") && destination.equals("d")){
            moveFromInitialToDestination();
        }else if(origin.equals("a") && destination.equals("o")){
            moveFromPivotToInitial();
        }else if(origin.equals("a") && destination.equals("d")){
            moveFromPivotToDestination();
        }else if(origin.equals("d") && destination.equals("o")){
            moveFromDestinationToInitial();
        }else{
            moveFromDestinationToPivot();
        }
    }
    public boolean checkContinue() {
        step +=1;
        int opcion = JOptionPane.showConfirmDialog(null, "PASO N.-" + step + "\n Vas avanzar?", "Pregunta", JOptionPane.YES_NO_OPTION);
        return opcion == JOptionPane.YES_OPTION;
    }
    private void recordMove(String move){
        StringBuilder invertido = new StringBuilder(move);
        if(towerManager.getMovesStack().isEmpty() || (!towerManager.getMovesStack().peek().equals(move) && !towerManager.getMovesStack().peek().equals(invertido.reverse().toString()))){
            towerManager.getMovesStack().push(move);
            isCancelled = true;
        }else{
            isCancelled = false;
            towerManager.getMovesStack().pop();
        }
    }
    private void resetMoves() {
        boolean paso = true;

        if (towerManager.getMovesStack().isEmpty()) {
            return;
        }

        while (!towerManager.getMovesStack().isEmpty() && paso) {
            if(towerManager.getTowerA().size() == towerManager.getDiscCount()){
                towerManager.getMovesStack().clear();
                break;
            }
            String move = towerManager.getMovesStack().pop();
            if ("IP".equals(move)) {
                moveFromPivotToInitial();
            }else if ("PI".equals(move)) {
                moveFromInitialToPivot();
            }else if ("ID".equals(move)) {
                moveFromDestinationToInitial();
            }else if ("DI".equals(move)) {
                moveFromInitialToDestination();
            }else if ("PD".equals(move)) {
                moveFromDestinationToPivot();
            }else{
                moveFromPivotToDestination();
            }
            if(isCancelled){
                towerManager.getMovesStack().pop();
            }
            paso = checkContinue();
        }
    }
    public void hanoiSolver(int n, String source, String auxiliary, String target) {
        if (n == 1) {
            performMove(source, target);
            shouldContinue = checkContinue();
        } else {
            hanoiSolver(n - 1, source, target, auxiliary);
            if (shouldContinue) {
                performMove(source, target);
                shouldContinue = checkContinue();
            }
            if (shouldContinue) {
                hanoiSolver(n - 1, auxiliary, source, target);
            }
        }
    }
    private void initializeGame(){
        step = 0;
        towerManager.getMovesStack().clear();
        towerManager.setMovesCount(0);
        numMovesLabel.setText(String.valueOf(towerManager.getMovesCount()));

        fPanel.removeAll();
        sPanel.removeAll();
        tPanel.removeAll();

        towerManager.getTowerA().clear();
        towerManager.getTowerB().clear();
        towerManager.getTowerC().clear();

        int discosSeleccionados = Integer.parseInt(discCombo.getSelectedItem().toString());

        towerManager.setDiscCount(discosSeleccionados);
        towerManager.setMinMoves();

        for (int i = 0; i < discosSeleccionados; i++) {
            String hashtags = "";
            JTextField field = new JTextField(10);
            for (int j = 0; j < discosSeleccionados-i; j++) {
                hashtags += "#";
            }
            towerManager.getTowerA().add(hashtags);
            field.setText(hashtags);
            field.setHorizontalAlignment(SwingConstants.CENTER);
            field.setMaximumSize(field.getPreferredSize());
            fPanel.add(field,0);
        }
        fPanel.revalidate();
        fPanel.repaint();

        minMovesLabel.setText(String.valueOf(towerManager.getMinMoves()));
    }
    private void resetGame(){
        towerManager.getMovesStack().clear();
        towerManager.setMovesCount(0);
        numMovesLabel.setText(String.valueOf(towerManager.getMovesCount()));

        fPanel.removeAll();
        sPanel.removeAll();
        tPanel.removeAll();

        towerManager.getTowerA().clear();
        towerManager.getTowerB().clear();
        towerManager.getTowerC().clear();

        fPanel.revalidate();
        fPanel.repaint();
    }
}
