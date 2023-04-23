package com.mycompany.compiortigoza;

import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;

public class Brain {
    private static Brain instance;
    
               //nombre afn    
    private Map<String, AFN> listaAutomatas = new HashMap<>();
    private Map<String, AFN> listaMega = new HashMap<>();
    private Map<String, String[][]> listaAFD = new HashMap<>();
    
    // Para prevenir inicializacion desde afuera
    private void Brain() {
    }
    
    public static Brain getInstance() {
        if (instance == null) {
            instance = new Brain();
        }
        return instance;
    }
    
    public void deleteAutomata(String key){
        listaAutomatas.remove(key);
    }
    
    public boolean listaAutomatasEmpty(){
        return listaAutomatas.isEmpty();
    }
    
    public Map<String, AFN> getListaAutomatas(){
        return listaAutomatas;
    }
    
    public void agregarAFN(String k, AFN val){
        if (listaAutomatas.containsKey(k)) {
            JOptionPane.showMessageDialog(null, "AFN con el mismo nombre ya existe", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        listaAutomatas.put(k, val);
    }
    
    public void agregarMega(String k, AFN val){
        if (listaMega.containsKey(k)) {
            JOptionPane.showMessageDialog(null, "AFN MEGA con el mismo nombre ya existe", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        listaMega.put(k, val);
    }
    
    public boolean listaMegasEmpty(){
        return listaMega.isEmpty();
    }
    
    public Map<String, AFN> getListaMegas(){
        return listaMega;
    }
    
    
    public void importarAFD(Map<String, String[][]> afd){
        listaAFD.putAll(afd);
    }
    
    public void agregarAFD(String k, String[][] val){
        if (listaAFD.containsKey(k)) {
            JOptionPane.showMessageDialog(null, "AFD con el mismo nombre ya existe", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        listaAFD.put(k, val);
    }
    
    public boolean listaAFDEmpty(){
        return listaAFD.isEmpty();
    }
    
    public Map<String, String[][]> getListaAFD(){
        return listaAFD;
    }
    
    public void imprimirLista(){
        System.out.println("Contents of listaAutomatas:");
        for (Map.Entry<String, AFN> entry : listaAutomatas.entrySet()) {
            String key = entry.getKey();
            AFN value = entry.getValue();
            System.out.println(key + ": " + value);
        }
    }
    
    
    
}
