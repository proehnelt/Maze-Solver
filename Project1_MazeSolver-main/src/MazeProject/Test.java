package MazeProject;

import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {
        
        MazeReader mr = new MazeReader("maze2.txt");
        System.out.println("Given MAZE");
        System.out.println("-------------------------------------------------------------");
        System.out.println(mr);
        MazeSolver ms = new MazeSolver(mr.getMaze());
        System.out.println("-------------------------------------------------------------");
        System.out.println("-->Length of path using Depth First Search: " + ms.DFS());
        System.out.println("-->Length of path using Breadth First Search: " + ms.BFS());
        System.out.println("-->Length of path using Greedy Best First Search: " + ms.GBFS());
        System.out.println("-------------------------------------------------------------");
        System.out.println("Conclusion:");
        if(ms.DFS()==-1 && ms.BFS()==-1 && ms.GBFS()==-1)
        {
        	System.out.println("No path Exists");
        }
        else if(ms.DFS()>ms.BFS() && ms.DFS()>ms.GBFS())
        {
        	System.out.println("DFS finds the shortest path when compared to BFS and GBFS for the above maze");
        }
        else if(ms.BFS()>ms.DFS() && ms.BFS()>ms.GBFS())
        {
        	System.out.println("BFS finds the shortest path when compared to DFS and GBFS for the above maze");
        }
        else if(ms.GBFS()>ms.DFS() && ms.GBFS()>ms.BFS())
        {
        	System.out.println("GBFS finds the shortest path when compared to DFS and BFS for the above maze");
        }
        else if(ms.BFS()==ms.DFS() && ms.BFS()==ms.GBFS())
        {
        	System.out.println("BFS, DFS and GBFS all finds the same shortest path for the above maze");
        }
        else if(ms.BFS()==ms.DFS())
        {
        	System.out.println("BFS and DFS finds the minimal shortest path when compared to the GBFS for the above maze");
        }
        else if(ms.BFS()==ms.GBFS())
        {
        	System.out.println("BFS and GBFS finds the minimal shortest path when compared to the DFS for the above maze");
        }
        else if(ms.DFS()==ms.GBFS())
        {
        	System.out.println("DFS and GBFS finds the minimal shortest path when compared to the BFS for the above maze");
        }                
        
        
        System.out.println("-------------------------------------------------------------");
    }
    
    public static String statusLine(MazeSolver ms)
    {
    	if(ms.DFS()==-1 && ms.BFS()==-1 && ms.GBFS()==-1)
        {
        	return ("No path Exists");
        }
        else if(ms.DFS()>ms.BFS() && ms.DFS()>ms.GBFS())
        {
        	return ("DFS finds the shortest path when compared to BFS and GBFS for the above maze");
        }
        else if(ms.BFS()>ms.DFS() && ms.BFS()>ms.GBFS())
        {
        	return ("BFS finds the shortest path when compared to DFS and GBFS for the above maze");
        }
        else if(ms.GBFS()>ms.DFS() && ms.GBFS()>ms.BFS())
        {
        	return ("GBFS finds the shortest path when compared to DFS and BFS for the above maze");
        }
        else if(ms.BFS()==ms.DFS() && ms.BFS()==ms.GBFS())
        {
        	return ("BFS, DFS and GBFS all finds the same shortest path for the above maze");
        }
        else if(ms.BFS()==ms.DFS())
        {
        	return ("BFS and DFS finds the minimal shortest path when compared to the GBFS for the above maze");
        }
        else if(ms.BFS()==ms.GBFS())
        {
        	return ("BFS and GBFS finds the minimal shortest path when compared to the DFS for the above maze");
        }
        else if(ms.DFS()==ms.GBFS())
        {
        	return ("DFS and GBFS finds the minimal shortest path when compared to the BFS for the above maze");
        }          
    	return null;
    }
}
