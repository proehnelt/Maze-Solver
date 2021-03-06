package MazeProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MazeApp extends JPanel {

    private Square[][] maze;
    private boolean loaded = false;
    private boolean started = false;
    private boolean running = false;
    private static MazeReader mazeReader;
    private static MazeSolver mazeSolver;
    private ArrayList<Integer> path = new ArrayList<>();
    private int pathIndex;
    private boolean solutionFound;
    Timer timer;
    String[] algSet = { "GBFS", "DFS", "BFS"};

    public MazeApp() {


        JPanel topPanel = new JPanel();
        JLabel label = new JLabel("File Name: ");
        JTextField textField = new JTextField(20);
        JButton load = new JButton("Load");
        JButton start = new JButton("Start");
        JButton step = new JButton("Step");
        JButton animate = new JButton("Step");
        JComboBox algList = new JComboBox(algSet);

        JPanel bottomPanel = new JPanel();
        JLabel status = new JLabel("No maze");
        JLabel algorithm = new JLabel("");


        step.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                pathIndex += 2;

                if (pathIndex > path.size() - 2) {
                    pathIndex = path.size() - 2;
                    if (solutionFound) {
                        status.setText("Solution complete: exit reachable at "
                                + path.get(path.size()-2) + "," + path.get(path.size()-1)
                                + " in " + path.size() / 2 + " moves.");
                    } else {
                        status.setText("Solution complete: exit not reachable");
                    }
                    timer.stop();
                    running = false;
                }
                repaint();
            }
        });
        load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    mazeReader = new MazeReader(textField.getText());
                    maze = mazeReader.getMaze();
                    mazeSolver = new MazeSolver(maze);
                    status.setText("Maze Loaded");
                    algorithm.setText("");
                    loaded = true;
                    started = false;
                    pathIndex = -1;
                    path = new ArrayList<>();
                    repaint();
                } catch (Error e) {
                    System.err.println("Invalid file name try again");
                    System.err.println(e.getStackTrace());
                }

            }
        });

        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String selectedAlgorithm;
                if (loaded && !started) {
                    started = true;
                    status.setText("Solution in progress: ");
                    ArrayList<Integer> bfsPath = new ArrayList<>();
                    ArrayList<Integer> dfsPath = new ArrayList<>();
                    ArrayList<Integer> gbfsPath = new ArrayList<>();
//                    mazeSolver.BFS(bfsPath);
//                    mazeSolver.DFS(dfsPath);
//                    solutionFound = mazeSolver.GBFS(gbfsPath);
//
//                    path = gbfsPath;
//                    selectedAlgorithm = "   GBFS";
//                    if (dfsPath.size() < path.size()) {
//                        path = dfsPath;
//                        selectedAlgorithm = "   DFS";
//                    }
//                    if (bfsPath.size() < path.size()) {
//                        path = bfsPath;
//                        selectedAlgorithm = "   BFS";
//                    }
//                    algorithm.setText(selectedAlgorithm);
                    
                    if (algList.getSelectedItem() == "DFS")
                    {
                    	algorithm.setText("DFS");
                    	path = dfsPath;
                    	solutionFound = mazeSolver.DFS(dfsPath);
                    }
                    if (algList.getSelectedItem() == "BFS")
                    {
                    	algorithm.setText("BFS");
                    	path = bfsPath;
                    	solutionFound = mazeSolver.BFS(bfsPath);
                    }
                    if (algList.getSelectedItem() == "GBFS")
                    {
                    	algorithm.setText("GBFS");
                    	path = gbfsPath;
                    	solutionFound = mazeSolver.GBFS(gbfsPath);
                    }
                }
            }
        });

        timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                step.doClick();
            }
        });
        animate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                running = !running;
                if (running) {
                    timer.start();
                } else {
                    timer.stop();
                }
            }
        });
        
        

        topPanel.add(label);
        topPanel.add(textField);
        topPanel.add(load);
        topPanel.add(start);
        //topPanel.add(step);
        topPanel.add(animate);
        topPanel.add(algList);

        bottomPanel.add(status);
        bottomPanel.add(algorithm);

        add(BorderLayout.NORTH, topPanel);
        add(BorderLayout.SOUTH, bottomPanel);

        pathIndex = -1;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        if (loaded) {
            g.translate(0, 70);

            int frameHeight = getBounds().getSize().height-70;
            int frameWidth = getBounds().getSize().width;
            int mazeRows = maze.length;
            int mazeCols = maze[0].length;
            int cellHeight = frameHeight / mazeRows;
            int cellWidth = frameWidth / mazeCols;

            for (int row = 0; row < maze.length; row++) {
                for (int col = 0; col < maze[0].length; col++) {
                    Color color;
                    switch(maze[row][col]) {
                        case WALL: color = Color.BLACK; break;
                        case OPEN_SPACE: color = Color.WHITE; break;
                        case EXIT: color = new Color(0xD86565); break;
                        case START: color = new Color(0x76CD79); break;
                        default:
                            throw new IllegalStateException("Unexpected value: " + maze[row][col]);
                    }
                    g.setColor(color);
                    g.fillRect(cellWidth * col, cellHeight * row, cellWidth, cellHeight);
                    g.setColor(Color.BLACK);
                    g.drawRect(cellWidth * col, cellHeight * row, cellWidth, cellHeight);
                }
            }

            for (int p = 0; p <= pathIndex-2; p += 2) {
                int pathX = path.get(p);
                int pathY = path.get(p+1);
                g.setColor(Color.GRAY);
                g.fillRect(pathX * cellWidth, pathY * cellHeight, cellWidth, cellHeight);
                g.setColor(Color.BLACK);
                g.drawRect(cellWidth * pathX, cellHeight * pathY, cellWidth, cellHeight);
            }
        }
    }

//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                MazeApp mazeApp = new MazeApp();
//                mazeApp.setVisible(true);
//            }
//        });
//    }
}
