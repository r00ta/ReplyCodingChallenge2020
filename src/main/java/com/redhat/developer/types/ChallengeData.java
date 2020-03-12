package com.redhat.developer.types;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

import com.redhat.developer.types.graph.Graph;
import com.redhat.developer.types.graph.Node;

public class ChallengeData {
    public CellType[][] office;

    public Map<Integer, Developer> developers;

    public Map<Integer, Manager> managers;

    public Developer[] developersArray;

    public Manager[] managersArray;

    public Graph graph;

    public SortedMap<WorkPotential, WorkPotentialKey> workPotentialMap;

    public Map<String, SortedMap<WorkPotential, WorkPotentialKey>> companyWorkPotentialMap = new HashMap<>();

    public void initialize(Integer rows, Integer cols){
        office = new CellType[rows][cols];
        developers = new HashMap<>();
        managers = new HashMap<>();
    }

    public void fillOffice(List<String> rows){
        graph = new Graph();

        for(int i = 0; i < rows.size(); i++){
            for(int j = 0; j < rows.get(i).length(); j++){
                CellType t;
                switch(rows.get(i).charAt(j)){
                    case '#':
                        t = CellType.NOT_AVAILABLE;
                        break;
                    case '_':
                        t = CellType.DEVELOPER;
                        putNewNodeInGraph(i,j, t, graph);
                        break;
                    case 'M':
                        t = CellType.MANAGER;
                        putNewNodeInGraph(i,j, t, graph);
                        break;
                    default:
                        throw new IllegalArgumentException("fodisjfiosdj");
                }
                office[i][j] = t;
            }
        }
    }

    public void putNewNodeInGraph(Integer row, Integer col, CellType type, Graph graph){
        Node node = new Node(col, row, type);
        graph.putNewNode(node);
    }

    public void addDeveloper(Integer i, Developer developer){
        developers.put(i, developer);
    };

    public void addManager(Integer i, Manager manager){
        managers.put(i, manager);
    };

    public void calculateWorkPotential(){
        developersArray = new Developer[developers.size()];
        developers.values().toArray(developersArray);
        workPotentialMap = new TreeMap<WorkPotential, WorkPotentialKey>(
                new Comparator<WorkPotential>() {
                    @Override
                    public int compare(WorkPotential workPotential, WorkPotential t1) {
                        return t1.workPotential - workPotential.workPotential;
                    }
                });

        for(int i = 0; i < developersArray.length; i++){
            for(int j = i + 1; j < developersArray.length; j++){
                WorkPotential score = Developer.calculateWorkPotential(developersArray[i], developersArray[j]);
                workPotentialMap.put(score, new WorkPotentialKey(i, j));
            }
        }
    }


    public void withinCompany(){
        Set<String> companies = developers.values().stream().map(x -> x.company).collect(Collectors.toSet());

        for (String company : companies){

            List<Developer> companyDevelopers = developers.values().stream().filter(f -> company.equals(f.company)).collect(Collectors.toList());

            developersArray = new Developer[companyDevelopers.size()];
            companyDevelopers.toArray(developersArray);

            for(int i = 0; i < developersArray.length; i++){
                for(int j = i + 1; j < developersArray.length; j++){
                    WorkPotential score = Developer.calculateWorkPotential(developersArray[i], developersArray[j]);
                    putInCompany(company, score, new WorkPotentialKey(i, j));
                }
            }
        }
    }

    public void putInCompany(String company, WorkPotential score, WorkPotentialKey key){
        if (!companyWorkPotentialMap.containsKey(company)){
            companyWorkPotentialMap.put(company,new TreeMap<WorkPotential, WorkPotentialKey>(
                    new Comparator<WorkPotential>() {
                        @Override
                        public int compare(WorkPotential workPotential, WorkPotential t1) {
                            return t1.workPotential - workPotential.workPotential;
                        }
                    }));
        }

        companyWorkPotentialMap.get(company).put(score, key);
    }

    public SortedMap<WorkPotential, WorkPotentialKey> getSortedMap(){
        return workPotentialMap;
    }
}
