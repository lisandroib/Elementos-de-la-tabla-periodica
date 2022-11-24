/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tabla.periodica.FrameTablaPeriodica;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.json.JSONObject;

/**
 *
 * @author Lisandro
 */
public class Elemento {
    public void buscar(){
       try{
            String api="https://periodictableapi.herokuapp.com/api/getElement/byName/"+Buscador.jTextField1.getText();
            URL url= new URL(api);
            HttpURLConnection con= (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.connect();

            int responseCode= con.getResponseCode();
            if(responseCode != 200){
                throw new RuntimeException("Ocurrio un error: "+ responseCode);

            }else{
                StringBuilder informationString= new StringBuilder();
                Scanner leer= new Scanner(url.openStream());

                while(leer.hasNext()){
                    informationString.append(leer.nextLine());
                }
                //System.out.println(informationString);
                JSONObject jsonObject= new JSONObject(informationString.toString());
                FrameElemento ventana= new FrameElemento();
                ventana.setVisible(true);
                
                FrameElemento.jNombre1.setText(jsonObject.getString("name"));
                FrameElemento.jConfElect.setText(jsonObject.getString("electronConfiguration"));
                FrameElemento.jNAtomico1.setText(jsonObject.getString("atomicNumber"));
                FrameElemento.jNMasico.setText(jsonObject.getString("atomicMass"));
                FrameElemento.jNOxidacion.setText(jsonObject.getString("oxidationNumbers"));
                FrameElemento.jClass1.setText(jsonObject.getString("class"));
                FrameElemento.jSimbolo1.setText(jsonObject.getString("symbol"));
                
                
                
            }
        }catch(Exception e){
            e.printStackTrace();
        } 
    }
    
}
