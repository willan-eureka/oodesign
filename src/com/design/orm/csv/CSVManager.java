package com.design.orm.csv;

import com.design.orm.BeanUtils;
import com.design.orm.FileManager;
import com.design.model.Entity;

import java.io.*;
import java.util.*;

/**
 * Implementation for database file in csv format
 * @param <T> Entity type to manage
 * @param <PK> Entity's primary key type
 */


public class CSVManager<T, PK extends Serializable> implements FileManager<T, PK> {

    private String entity;
    private String entityTmp;
    private Class<T> persistenceClass;

    public CSVManager(Class<T> persistenceClass) {
        this.persistenceClass = persistenceClass;
        String name = this.persistenceClass.getSimpleName().toLowerCase();
        this.entity = name + ".csv";
        File file = new File(entity);
        if (file.exists()) {
            file.delete();
        }
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.entityTmp = name + "_tmp.csv";
    }

    public T save(T o) {
        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(entity, Boolean.TRUE));
            Entity model = (Entity) o;
            model.setId(count() + 1);
            model.setDate(new Date());
            bufferedWriter.write(BeanUtils.beanToFormat(o, persistenceClass));
            bufferedWriter.newLine();
            return o;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeStream(null, bufferedWriter);
        }

        return null;
    }

    public void update(T o) {
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(entity));
            bufferedWriter = new BufferedWriter(new FileWriter(entityTmp));

            Entity omodel = (Entity) o;
            omodel.setDate(new Date());
            String row = BeanUtils.beanToFormat(o, persistenceClass);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.split(",")[0].equals(omodel.getId().toString())) {
                    line = row;
                }
                if (!line.isEmpty()) {
                    bufferedWriter.write(line);
                    bufferedWriter.newLine();
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeStream(bufferedReader, bufferedWriter);
        }

        File readerFile = new File(entity);
        readerFile.delete();

        File writerFile = new File(entityTmp);
        writerFile.renameTo(readerFile);
    }

    public void delete(PK id) {
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(entity));
            bufferedWriter = new BufferedWriter(new FileWriter(entityTmp));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (!line.split(",")[0].equals(id.toString())) {
                    bufferedWriter.write(line);
                    bufferedWriter.newLine();
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeStream(bufferedReader, bufferedWriter);
        }

        File readerFile = new File(entity);
        readerFile.delete();

        File writerFile = new File(entityTmp);
        writerFile.renameTo(readerFile);
    }

    public T get(PK id) {
        Map<String, String> properties = new HashMap<String, String>();
        properties.put("id", id.toString());
        List<T> list = search(properties, null, null);
        return list.isEmpty() ? null : list.get(0);
    }

    public List<T> list(Long page, Long size) {
        return search(new HashMap<String, String>(), page, size);
    }

    public List<T> search(Map<String, String> properties, String sort) {
        List<T> list = search(properties, null, null);
        sortList(list, sort);
        return list;
    }

    private List<T> search(Map<String, String> properties, Long page, Long size) {
        List<T> list = new ArrayList<T>();
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(entity));
            String line;

            Long index = (page != null && size != null) ? -1L : null;
            Long i = (page != null && size != null) ? ((page - 1) * size) : null;
            Long n = (page != null && size != null) ? i + size : null;
            while ((line = bufferedReader.readLine()) != null) {
                if (index != null) index++;
                if (index != null && index < i) {
                    continue;
                } else if (index != null && index > n - 1) {
                    break;
                }

                Map<String, String> m = BeanUtils.makeMap(line, persistenceClass);
                if (properties.isEmpty() || (m.keySet().containsAll(properties.keySet()) && m.values().containsAll(properties.values()))) {
                    T o = BeanUtils.makeBean(m, persistenceClass);
                    list.add(o);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeStream(bufferedReader, null);
        }

        return list;
    }

    public long count() {
        BufferedReader bufferedReader = null;
        long lines = 0;
        try {
            bufferedReader = new BufferedReader(new FileReader(entity));
            while (bufferedReader.readLine() != null) {
                lines++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeStream(bufferedReader, null);
        }

        return lines;
    }

    private void sortList(List<T> list, String sort) {
        List<Entity> entitys = (List<Entity>) list;
        if (sort.equals("name")) {
            Collections.sort(entitys, new Comparator<Entity>() {
                @Override
                public int compare(Entity o1, Entity o2) {
                    return o1.getName().compareTo(o2.getName());
                }
            });
        } else {
            Collections.sort(entitys, new Comparator<Entity>() {
                @Override
                public int compare(Entity o1, Entity o2) {
                    return o2.getDate().compareTo(o1.getDate());
                }
            });
        }
    }

    private void closeStream(BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
        try {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (bufferedWriter != null) {
                bufferedWriter.flush();
                bufferedWriter.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
