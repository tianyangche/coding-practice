package com.tianyangche.practice.others;

import java.util.*;

/**
 * Created by tianyangche on 3/23/16.
 */
public class ReconstructItinerary {
    public List<String> findItinerary(String[][] tickets) {
        Map<String, List<String>> map = new HashMap<String, List<String>>();


        for (String[] ticket : tickets) {
            if (!map.containsKey(ticket[0])) {
                map.put(ticket[0], new ArrayList<>());
            }
            if (!map.containsKey(ticket[1])) {
                map.put(ticket[1], new ArrayList<>());
            }

            map.get(ticket[0]).add(ticket[1]);
        }
        int size = map.size();
        List<String> res = new ArrayList<String>();
        List<String> temp = new ArrayList<String>();
        temp.add("JFK");
        dfs(res, temp, map, tickets.length, "JFK");
        return res;
    }
    private boolean dfs(List<String> res, List<String> temp, Map<String, List<String>> map, int remaining, String city) {
        if (remaining == 0) {
            res.addAll(temp);
            return true;
        }

        if (map.get(city).isEmpty()) {
            return false;
        }

        Collections.sort(map.get(city));


        List<String> potentials = map.get(city);
        for (int i = 0; i < potentials.size(); i++) {
            String potential = potentials.get(i);
            temp.add(potential);
            potentials.remove(i);
            if (dfs(res, temp, map, remaining - 1, potential)) {
                return true;
            }
            potentials.add(i, potential);
            temp.remove(temp.size() - 1);
        }
        return false;
    }

    public static void main(String[] args) {
        ReconstructItinerary reconstructor = new ReconstructItinerary();
//        String[][] tickets = {{"MUC", "LHR"}, {"JFK", "MUC"}, {"SFO", "SJC"}, {"LHR", "SFO"}};
        String[][] tickets = {{"CBR","JFK"},{"TIA","EZE"},{"AUA","TIA"},{"JFK","EZE"},{"BNE","CBR"},{"JFK","CBR"},{"CBR","AUA"},{"EZE","HBA"},{"AXA","ANU"},{"BNE","EZE"},{"AXA","EZE"},{"AUA","ADL"},{"OOL","JFK"},{"BNE","AXA"},{"OOL","EZE"},{"EZE","ADL"},{"TIA","BNE"},{"EZE","TIA"},{"JFK","AUA"},{"AUA","EZE"},{"ANU","ADL"},{"TIA","BNE"},{"EZE","OOL"},{"ANU","BNE"},{"EZE","ANU"},{"ANU","AUA"},{"BNE","ANU"},{"CNS","JFK"},{"TIA","ADL"},{"ADL","AXA"},{"JFK","OOL"},{"AUA","ADL"},{"ADL","TIA"},{"ADL","ANU"},{"ADL","JFK"},{"BNE","EZE"},{"ANU","BNE"},{"JFK","BNE"},{"EZE","AUA"},{"EZE","AXA"},{"AUA","TIA"},{"ADL","CNS"},{"AXA","AUA"}};
        List<String> res = reconstructor.findItinerary(tickets);
        System.out.println(res);
    }
}
