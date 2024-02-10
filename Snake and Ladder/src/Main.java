import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Main {
    public static void main(String[] args)
            throws IOException {
        File file = new File("Snake and Ladder/src/input.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        Map<Integer,Integer> positions = new HashMap<>();
        List<String> players = new ArrayList<>();
        int n = Integer.parseInt(br.readLine());
        for(int i=0;i<n;++i){
            String[] pos = br.readLine().split(" ");
            positions.put(Integer.parseInt(pos[0]),Integer.parseInt(pos[1]));
        }
        n = Integer.parseInt(br.readLine());
        for(int i=0;i<n;++i){
            String[] pos = br.readLine().split(" ");
            positions.put(Integer.parseInt(pos[0]),Integer.parseInt(pos[1]));
        }
        n = Integer.parseInt(br.readLine());
        int[] playersPosition = new int[n];

        for(int i=0;i<n;++i){
            String var = br.readLine();
            players.add(var);
        }
        int idx = 0;//to maintain the player's index
        Random ran = new Random();
        while(playersPosition[idx] < 100){
            int next = ran.nextInt(1,7);
            if(playersPosition[idx]+next > 100){
                System.out.println(String.format("%s wait until %d comes up",players.get(idx),100-playersPosition[idx]));
            }
            else {
                System.out.println(String.format("%s rolled a move from %d to %d",players.get(idx) ,playersPosition[idx],
                        positions.getOrDefault(playersPosition[idx] + next, playersPosition[idx] + next)));
                playersPosition[idx] += next;
            }
            if(positions.containsKey(playersPosition[idx])) {
                playersPosition[idx] = positions.get(playersPosition[idx]);
            }
            if(playersPosition[idx] == 100){
                break;
            }
            if(next != 6){
                idx = (idx+1)%players.size();
            }
        }
        System.out.println(String.format("%s wins the game", players.get(idx)));
    }
}