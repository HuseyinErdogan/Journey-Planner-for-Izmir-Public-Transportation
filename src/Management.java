import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class Management {

	public static void main(String[] args) throws IOException {	
		DijkstraAlgorithm dijkstra = new DijkstraAlgorithm();
		File testTXT = new File("/test_stops.txt");				
		BufferedReader br = new BufferedReader(new FileReader(testTXT));
			
		while(true) {
			System.out.println();
			System.out.println("-----------------");
			System.out.println();
			System.out.println("Please enter your command.");
			System.out.println("1-Fewer stops");
			System.out.println("2-Minimum distance");
			System.out.println("3-Read test stops");
			Scanner sc = new Scanner(System.in);
			int command=sc.nextInt();

			
			if (command==1) {
				
				System.out.println();
				System.out.print("Please enter origin stop id: ");
				int origin = sc.nextInt();
				System.out.println();
				System.out.print("Please enter destination stop id: ");
				int destination = sc.nextInt();
				dijkstra.shortestPath(dijkstra.graph.getVertex(origin), dijkstra.graph.getVertex(destination),1,5);
			}
			else if(command==2){
				
				System.out.println();
				System.out.print("Please enter origin stop id: ");
				int origin = sc.nextInt();
				System.out.println();
				System.out.print("Please enter destination stop id: ");
				int destination = sc.nextInt();
				dijkstra.shortestPath(dijkstra.graph.getVertex(origin), dijkstra.graph.getVertex(destination),2,5);
				
			}
			else if(command==3) {
				String st; 
			    String[] stArray = new String[5];
			    st=br.readLine();
			    int resultCount1=0, resultCount2=0;
			    int count1=0,count2=0;
			    double time1=0,time2=0;
			   

						    
			    while ((st = br.readLine()) != null) {
					stArray=st.split(";");
					if (Integer.parseInt(stArray[4])==1) {
						count1++;
					    long startTime = System.nanoTime();
						resultCount1+=dijkstra.shortestPath(dijkstra.graph.getVertex(Integer.parseInt(stArray[0])), dijkstra.graph.getVertex(Integer.parseInt(stArray[1])),1,5);
						long endTime   = System.nanoTime();
						long totalTime = endTime - startTime;
					    double elapsedTimeInSecond = (double) totalTime / 1000000000;
						time1+=elapsedTimeInSecond;

					}
					else {
						count2++;
					    long startTime = System.nanoTime();
						resultCount2+=dijkstra.shortestPath(dijkstra.graph.getVertex(Integer.parseInt(stArray[0])), dijkstra.graph.getVertex(Integer.parseInt(stArray[1])),2,5);
						long endTime   = System.nanoTime();
						long totalTime = endTime - startTime;
					    double elapsedTimeInSecond = (double) totalTime / 1000000000;
						time2+=elapsedTimeInSecond;
					}
					
				}
			    int avarageCount1=resultCount1/count1;
			    int avarageCount2=resultCount2/count2;
			    double avarageTime1=time1/count1;
			    double avarageTime2=time2/count2;
			    System.out.println("avarageCount1: "+avarageCount1+"  avarageTime1: "+avarageTime1);
			    System.out.println("avarageCount2: "+avarageCount2+"  avarageTime2: "+avarageTime2);
			}
			else
				System.out.println("WRONG COMMAND !");
			
		}

	}

}
