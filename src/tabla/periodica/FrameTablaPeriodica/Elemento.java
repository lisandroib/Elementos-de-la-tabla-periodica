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
            String api="https://neelpatel05.pythonanywhere.com/element/atomicname?atomicname="+Buscador.jTextField1.getText();
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

                JSONObject jsonObject= new JSONObject(informationString.toString());
                FrameElemento ventana= new FrameElemento();
                ventana.setVisible(true);
                
                FrameElemento.jNombre1.setText(jsonObject.getString("name"));
                FrameElemento.jConfElect.setText(jsonObject.getString("electronicConfiguration"));
                FrameElemento.jNAtomico1.setText(String.valueOf(jsonObject.getInt("atomicNumber")));
                FrameElemento.jNMasico.setText(jsonObject.getString("atomicMass"));
                FrameElemento.jNOxidacion.setText(jsonObject.getString("oxidationStates"));
                FrameElemento.jClass1.setText(jsonObject.getString("bondingType"));
                FrameElemento.jSimbolo1.setText(jsonObject.getString("symbol"));
                
                
                
            }
        }catch(Exception e){
            e.printStackTrace();
        } 
    }
    
}
