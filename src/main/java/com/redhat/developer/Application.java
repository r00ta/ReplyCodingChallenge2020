package com.redhat.developer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.stream.Collectors;

import com.redhat.developer.types.ChallengeData;
import com.redhat.developer.types.Developer;
import com.redhat.developer.types.Manager;
import com.redhat.developer.types.WorkPotential;
import com.redhat.developer.types.WorkPotentialKey;
import com.redhat.developer.types.graph.Graph;
import com.redhat.developer.types.graph.Node;

public class Application {

    public static void main(String[] args) throws IOException {
        solve(new InputStreamReader(System.in), new OutputStreamWriter(System.out));
    }

    public static void solve(InputStreamReader is, OutputStreamWriter os) throws IOException {
        ChallengeData data = readData(is);

        data.calculateWorkPotential();

        SortedMap<WorkPotential, WorkPotentialKey> sortedMap = data.getSortedMap();


        Graph graph = data.graph;

        graph.buildSortedMap();

        List<Manager> managers = data.managers.values().stream().collect(Collectors.toList());

        int i = 0;
        for(Node node : graph.managerMap.values()){
            Manager manager = managers.get(i);
           manager.col = node.col;
           manager.row = node.row;
           i++;
        }


        Iterator<WorkPotentialKey> key = data.workPotentialMap.values().stream().collect(Collectors.toList()).iterator();
        Map<Integer, Developer> developers = data.developers;

        i = 0;
        int j = 0;

        for(Node node : graph.developerMap.values()){

            WorkPotentialKey aa = key.next();
            Developer d = developers.get(aa.x);
            if (node.visited){
                continue;
            }

            d.col = node.col;
            d.row = node.row;
            node.visited = true;

            for (Node nn : node.neighbours){
                if (node.visited){
                    continue;
                }

                Developer dd = developers.get(aa.y);
                dd.col = nn.col;
                dd.row = nn.row;
                nn.visited = true;
                break;
            }
        }

        List<Developer> developerss = data.developers.values().stream().collect(Collectors.toList());

        for(Developer d : developerss){
            if (d.col != null){
                os.write(String.format("%d %d\n", d.row, d.col));
            }
            else{
                os.write("X\n");
            }
        }

        int fanculo = 0;

        for(Manager m : managers){
            fanculo++;

            if (m.col != null){
                os.write(String.format("%d %d\n", m.row, m.col));
            }
            else{
                if (fanculo == managers.size()){
                    os.write("X");
                }
                else{
                    os.write("X\n");
                }
            }
        }

        os.close();

    }



    public static ChallengeData readData(InputStreamReader is) throws IOException {
        BufferedReader reader = new BufferedReader(is);

        String officeDims = reader.readLine();
        String[] dims = officeDims.split(" ");

        Integer cols = Integer.valueOf(dims[0]);
        Integer rows = Integer.valueOf(dims[1]);

        ArrayList<String> officeRows = new ArrayList<>();
        for(int i = 0; i < rows; i++){
            officeRows.add(reader.readLine());
        }

        ChallengeData data = new ChallengeData();
        data.initialize(rows, cols);
        data.fillOffice(officeRows);

        Integer numberOfDevelopers = Integer.valueOf(reader.readLine());

        for (int i = 0; i < numberOfDevelopers; i++){
            String[] set = reader.readLine().split(" ");
            String company = set[0];
            Integer bonus = Integer.valueOf(set[1]);
            Integer numberOfSkills = Integer.valueOf(set[2]);
            Set<String> skills = new HashSet<>();
            for(int j = 1; j <= numberOfSkills; j++){
                skills.add(set[2 + j]);
            }
            data.addDeveloper(i, new Developer(i, company, bonus, skills));
        }

        Integer numberOfManagers = Integer.valueOf(reader.readLine());
        for (int i = 0; i < numberOfManagers; i++){
            String[] set = reader.readLine().split(" ");
            String company = set[0];
            Integer bonus = Integer.valueOf(set[1]);

            data.addManager(i, new Manager(i, company, bonus));
        }
        return data;
    }
}
