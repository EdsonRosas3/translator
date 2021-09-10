package com.example.translator1.service;

import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.ArrayList;
@Service
public class FileService {
    private ArrayList<String> lines;
    private ArrayList<String> invested;
    public FileService(){
        lines=new ArrayList<String>();
        invested = new ArrayList<String>();
    }
    public boolean invertFile() throws IOException {
        System.out.println("Hola DESDE SERVICIO");
        String inputFileName = "src/main/resources/Original.txt";
        String outputFileName = "src/main/resources/estrofasEnOrdenInverso.txt";
        File outputFile = new File(outputFileName);
        OutputStream out = new FileOutputStream(outputFileName);

        drawTheLines(inputFileName);
        invertSongByVerse();
        String investedString = toString(invested);

        StreamUtils.copy(investedString, StandardCharsets.UTF_8,out);

        return  outputFile.exists();
    }
    public boolean imprimirData() throws IOException {
        String imprimir = "Numero de estrofas: "+numberOfStanzas()+"\n"+"Palabra mas repetida: "+mostRepeatedWord();
        String outputFileName = "src/main/resources/statistics.txt";
        File outputFile = new File(outputFileName);
        OutputStream out = new FileOutputStream(outputFileName);
        StreamUtils.copy(imprimir, StandardCharsets.UTF_8,out);
        System.out.print(imprimir);
        return outputFile.exists();
    }
    public boolean remplaceWord() throws IOException {
        String wordOld = mostRepeatedWord();
        String cancion = toString(lines);
        cancion = cancion.replace(wordOld,"you");

        String outputFileName = "src/main/resources/finaloutput.txt";
        File outputFile = new File(outputFileName);
        OutputStream out = new FileOutputStream(outputFileName);
        StreamUtils.copy(cancion, StandardCharsets.UTF_8,out);
        return outputFile.exists();

    }
    public String mostRepeatedWord(){
        String outputFileName = "src/main/resources/estrofasEnOrdenInverso.txt";
        drawTheLines(outputFileName);
        String investedString = toString(lines);

        String palabras [] = investedString.split(" ");
        String palabrasB [] = investedString.split(" ");

        int cantidad = palabras.length;
        String resultado = "";
        int contadorMasRepet =  0;

        for (int i = 0; i < cantidad; i++) {
            int contador = 0;
//          resultado += palabras[i];
            String palabra = palabras[i];

            for (int j = 0; j < cantidad; j++) {

                if (palabra.equalsIgnoreCase(palabrasB[j])){
                    contador++;
                    palabras[j] = "";

                }
            }

            if ((contador > 1)&& (contador > contadorMasRepet)){
                resultado = palabra;
                contadorMasRepet = contador;
                System.out.print(palabras[i]);
            }
            else if ((contador > 1)&& (contador == contadorMasRepet)){
                resultado += " " + palabra;
            }
        }
        if (resultado == "")
            resultado = "No hay palabra repetida";

        return resultado;
    }
    public int numberOfStanzas(){
        int count = 0;
        lines.clear();
        String inputFileName = "src/main/resources/estrofasEnOrdenInverso.txt";
        drawTheLines(inputFileName);
        boolean withinStanza = false;
        for (String line :lines) {
            if(line.equals("SPACE")){
                count+=1;
            }
        }
        return count+1;
    }
    public String toString(ArrayList<String> list){
        String result = "";
        for (String line : list) {
            if(line.equals("SPACE")){
                result+="\n";
            }else{
                result+=line+"\n";
            }
        }

        return result;
    }

    public void invertSongByVerse(){
        ArrayList<String> verse = new ArrayList<String>();
        for (int i = lines.size()-1; i>=0 ; i--){

            if(lines.get(i).equals("SPACE")){
                for (int j = verse.size()-1 ; j>=0 ; j--){
                    invested.add(verse.get(j));
                }
                invested.add("SPACE");
                verse.clear();
            }else{
                verse.add(lines.get(i));
            }
        }
        if(verse.size()>0){
            for (int j = verse.size()-1 ; j>=0 ; j--){
                invested.add(verse.get(j));
            }
            verse.clear();
        }

    }
    public String convertedString(InputStream fileInputStream){
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
        Stream<String> streamOfString= new BufferedReader(inputStreamReader).lines();
        String cadena = streamOfString.collect(Collectors.joining());
        return cadena;
    }
    public void drawTheLines(String nameFile){
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(nameFile));

            String texto = br.readLine();
            lines.add(texto);
            while(texto != null)
            {

                texto = br.readLine();
                if(!texto.equals("")){
                    lines.add(texto);
                }
                else{
                    lines.add("SPACE");
                }
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("Error: Fichero no encontrado");
            System.out.println(e.getMessage());
        }
        catch(Exception e) {
            System.out.println("Error de lectura del fichero");
            System.out.println(e.getMessage());
        }
        finally {
            try {
                if(br != null)
                    br.close();
            }
            catch (Exception e) {
                System.out.println("Error al cerrar el fichero");
                System.out.println(e.getMessage());
            }
        }
    }

}
