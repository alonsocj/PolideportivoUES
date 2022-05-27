package sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.WebServices.ConsultarEvento;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Alonso.TipoEvento.TipoEvento;
import sv.edu.ues.fia.eisi.pdm115.g10.polideportivoues.Chris.Evento.Evento;

public class EventoService {

    public static String obtenerRespuestaPeticion(String url, Context context) {

        String respuesta = "";

        //Establecemos los tiempos de espera del servicio
        HttpParams parametros = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(parametros, 3000);
        HttpConnectionParams.setSoTimeout(parametros, 5000);

        //Creamos los objetos de conexion
        HttpClient cliente = new DefaultHttpClient(parametros);
        HttpGet httpGet = new HttpGet(url);

        try {
            HttpResponse httpRespuesta = cliente.execute(httpGet);
            StatusLine estado = httpRespuesta.getStatusLine();
            int codigoEstado = estado.getStatusCode();
            if (codigoEstado == 200) {
                HttpEntity entidad = httpRespuesta.getEntity();
                respuesta = EntityUtils.toString(entidad);
            }
        } catch (Exception e) {
            Toast.makeText(context, "Error en la conexion", Toast.LENGTH_SHORT).show();
            //Desplegando el error en el LogCat
            Log.v("Error de Conexion", e.toString());
        }

        return respuesta;

    }

    public static String obtenerRespuestaPost(String url, JSONObject jsonObject, Context context){

        String respuesta = "";

        try{
            HttpParams parametros = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(parametros, 3000);
            HttpConnectionParams.setSoTimeout(parametros, 5000);

            HttpClient client = new DefaultHttpClient(parametros);
            HttpPost httpPost = new HttpPost(url);
            httpPost.setHeader("content-type","application/json");
            StringEntity nuevaEntidad = new StringEntity(jsonObject.toString());
            httpPost.setEntity(nuevaEntidad);
            Log.v("Peticion",url);
            Log.v("POST", httpPost.toString());
            HttpResponse httpResponse = client.execute(httpPost);
            StatusLine estado = httpResponse.getStatusLine();

            int codigoEstado = estado.getStatusCode();
            if(codigoEstado == 200){
                respuesta = Integer.toString(codigoEstado);
                Log.v("respuesta", respuesta);
            }else{
                Log.v("respuesta", Integer.toString(codigoEstado));
            }
        }catch (Exception e){
            Toast.makeText(context, "Error en la conexion", Toast.LENGTH_LONG).show();

            //Mostrando el error en el LogCat
            Log.v("Error de conexion", e.toString());
        }

        return respuesta;
    }

    public static  List<Evento> obtenerEventosExternos(String json, Context context){

        List<Evento> conjuntoEventos = new ArrayList<Evento>();

        try{
            JSONArray eventosJSON = new JSONArray(json);

            for(int i = 0; i <eventosJSON.length(); i++){
                JSONObject jsonObject = eventosJSON.getJSONObject(i);
                Evento evento = new Evento();
                evento.setIdEvento(jsonObject.getString("IDEVENTO"));
                evento.setNomEvento(jsonObject.getString("NOMEVENTO"));
                evento.setCostoEvento(jsonObject.getInt("COSTOEVENTO"));
                evento.setCantidadAutorizada(jsonObject.getInt("CANTIDADAUTORIZADA"));
                conjuntoEventos.add(evento);
            }

            return conjuntoEventos;

        } catch (Exception e) {
            Toast.makeText(context, "Error de parseo de JSON de eventos", Toast.LENGTH_SHORT).show();
            return null;
        }

    }

    public static  List<TipoEvento> obtenerTipoEventosExternos(String json, Context context){

        List<TipoEvento> conjuntoEventos = new ArrayList<TipoEvento>();

        try{
            JSONArray eventosJSON = new JSONArray(json);

            for(int i = 0; i <eventosJSON.length(); i++){
                JSONObject jsonObject = eventosJSON.getJSONObject(i);
                TipoEvento tipoEvento = new TipoEvento();
                tipoEvento.setNombreTipoE(jsonObject.getString("NOMTIPOE"));
                conjuntoEventos.add(tipoEvento);
            }

            return conjuntoEventos;

        } catch (Exception e) {
            Toast.makeText(context, "Error de parseo de JSON de tipoeventos", Toast.LENGTH_SHORT).show();
            return null;
        }

    }



}