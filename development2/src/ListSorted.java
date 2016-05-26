import java.io.*;
import java.util.*;

public class ListSorted implements StudentsSorter{

    @Override
    public void process(InputStream input, OutputStream output){

        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(input, "UTF-8"));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output, "UTF-8"));
            HashMap<String, ArrayList<String>> map = new HashMap<>();
            ArrayList<String> k;

            //запись в map (группа - студенты), сразу распредел€€ по группам
            String s = "";
            String line = reader.readLine().substring(1);
            while (line != null) {
                s = s + " " + line;

                String[] new_s = s.split(":");
                k = new ArrayList<>();
                if (map.containsKey(new_s[1]))
                    k = map.get(new_s[1]);
                k.add(new_s[0]);
                map.put(new_s[1], k);

                line = reader.readLine();
                s = "";
            }

            //сортировка групп по кол-ву студентов
            List<Map.Entry<String, ArrayList<String>>> list = new LinkedList<>(map.entrySet());
            Collections.sort(list, new Comparator<Map.Entry<String, ArrayList<String>>>() {
                @Override
                public int compare(Map.Entry<String, ArrayList<String>> o1, Map.Entry<String, ArrayList<String>> o2) {
                    return o2.getValue().size() - o1.getValue().size();
                }
            });

            //сортировка имен по алфавиту и запись в выходной файл
            for (Map.Entry<String, ArrayList<String>> entry : list) {
                k = entry.getValue();
                Collections.sort(k);
                entry.setValue(k);
                writer.write(entry.getKey() + ":");
                for (String a : entry.getValue()) {
                    writer.write(a);
                    if (entry.getValue().size() - 1 != entry.getValue().indexOf(a))
                        writer.write(",");
                }
                writer.newLine();
            }

            writer.close();
            reader.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
