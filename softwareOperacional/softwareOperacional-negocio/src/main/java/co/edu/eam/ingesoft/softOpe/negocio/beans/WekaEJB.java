package co.edu.eam.ingesoft.softOpe.negocio.beans;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import co.edu.eam.ingesoft.softOper.entidades.AuditoriaJson;
import co.edu.eam.ingesoft.softOper.entidades.Cluster;
import co.edu.eam.ingesoft.softOper.entidades.Objeto;
import weka.associations.Apriori;
import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48;
import weka.clusterers.Cobweb;
import weka.clusterers.SimpleKMeans;
import weka.core.Instances;

@LocalBean
@Stateless
public class WekaEJB {

	DecimalFormat formato = new DecimalFormat("#.##");

	/**
	 * 
	 * <Describir el Metodo>
	 *
	 * @author EAM Santiago Gaviria Oliveros Email: sangav96@gmail.com
	 * @param datos
	 * @return
	 * @date 24/02/2018
	 * @version <Version del metodo>
	 *
	 */
	public String mineriaApriori(String datos) {

		// los datos tipo String lo convertimos a un StringReader
		StringReader sr = new StringReader(datos);

		// el StringReader lo convertimos a un Buffer
		BufferedReader br = new BufferedReader(sr);

		try {
			// definimos un objeto que tendra los datos a clasificar
			Instances data = new Instances(br);

			// Seleccionamos cual sera el atributo clase, el cual es el atributo clase
			data.setClassIndex(data.numAttributes() - 1);
			// cerramos el objeto Buffer
			br.close();

			return apriori(data);

		} catch (IOException e) {
			return "El error es " + e.getMessage();
		}

	}

	/**
	 * 
	 * <Describir el Metodo>
	 *
	 * @author EAM Santiago Gaviria Oliveros Email: sangav96@gmail.com
	 * @param data
	 * @return
	 * @date 24/02/2018
	 * @version <Version del metodo>
	 *
	 */
	private String apriori(Instances data) {
		try {
			// Creamos un objeto de asociación por apriori
			Apriori aso = new Apriori();

			// creamos el descriptivo apriori con los datos
			aso.buildAssociations(data);

			// Se cargan los resultados de la asociacion apriori
			String resApriori = "<br><b><center>Resultados Asociacion "
					+ "Apriori</center><br><br>El modelo de asociacion " + "generado indica los siguientes resultados:"
					+ "<br><br></b>";

			// Obtenemos resultados
			for (int i = 0; i < aso.getAssociationRules().getRules().size(); i++) {
				resApriori = resApriori + "<b>" + (i + 1) + ". Si </b>"
						+ aso.getAssociationRules().getRules().get(i).getPremise().toString();
				resApriori = resApriori + " <b>Entonces</b> "
						+ aso.getAssociationRules().getRules().get(i).getConsequence().toString();
				resApriori = resApriori + "<b> Con un </b>"
						+ (int) (aso.getAssociationRules().getRules().get(i).getPrimaryMetricValue() * 100)
						+ "% de probabilidad<br>";

			}

			return resApriori;

		} catch (Exception e) {
			return "El error es " + e.getMessage();
		}
	}

	/**
	 * 
	 * <Describir el Metodo>
	 *
	 * @author EAM Santiago Gaviria Oliveros Email: sangav96@gmail.com
	 * @param datos
	 * @return
	 * @date 24/02/2018
	 * @version <Version del metodo>
	 *
	 */
	public Objeto mineriaCluster(String datos) {
		
		System.out.println(datos);

		// los datos tipo String lo convertimos a un StringReader
		StringReader sr = new StringReader(datos);

		// el StringReader lo convertimos a un Buffer
		BufferedReader br = new BufferedReader(sr);

		try {
			// definimos un objeto que tendra los datos a clasificar
			Instances data = new Instances(br);

			// cerramos el objeto Buffer
			br.close();

			return cluster(data);

		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * 
	 * <Describir el Metodo>
	 *
	 * @author EAM Santiago Gaviria Oliveros Email: sangav96@gmail.com
	 * @param data
	 * @return
	 * @date 24/02/2018
	 * @version <Version del metodo>
	 *
	 */
	private Objeto cluster(Instances data) {
		try {

			ArrayList<Cluster> elementos = new ArrayList<Cluster>();

			final Cobweb coweb = new Cobweb();

			coweb.buildClusterer(data);

			SimpleKMeans kmeans = new SimpleKMeans();

			kmeans.setSeed(10);

			// important parameter to set: preserver order, number of cluster.
			kmeans.setPreserveInstancesOrder(true);
			kmeans.setNumClusters(5);

			kmeans.buildClusterer(data);

			// This array returns the cluster number (starting with 0) for each instance
			// The array has as many elements as the number of instances
			Instances instances = kmeans.getClusterCentroids();
			
			Objeto objeto = new Objeto();

			for (int i = 0; i< kmeans.getNumClusters(); i++) {
				System.out.println("Instances " + instances.instance(i));
				String[] instanceData = (instances.instance(i)).toString().split(",");
				Cluster clu = new Cluster();
				//clu.setAudi(new AuditoriaJson(instanceData[2],
				//		instanceData[5], instanceData[4], instanceData[1], instanceData[3]));
				clu.setName("Cluster " + i);
				clu.setSize(kmeans.getClusterSizes()[i]);
				elementos.add(clu);
			}
			
			objeto.setName("Auditoria");
			objeto.setChildren(elementos);

			return objeto;
		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}

	}

	/**
	 * 
	 * <Describir el Metodo>
	 *
	 * @author EAM Santiago Gaviria Oliveros Email: sangav96@gmail.com
	 * @param datos
	 * @return
	 * @date 24/02/2018
	 * @version <Version del metodo>
	 *
	 */
	public String mineriaArbol(String datos) {

		// los datos tipo String lo convertimos a un StringReader
		StringReader sr = new StringReader(datos);

		// el StringReader lo convertimos a un Buffer
		BufferedReader br = new BufferedReader(sr);

		try {
			// definimos un objeto que tendra los datos a clasificar
			Instances data = new Instances(br);

			// Seleccionamos cual sera el atributo clase, el cual es el atributo clase
			data.setClassIndex(data.numAttributes() - 1);
			// cerramos el objeto Buffer
			br.close();

			return arbolJ48(data);

		} catch (IOException e) {
			return "El error es " + e.getMessage();
		}

	}

	/**
	 * 
	 * <Describir el Metodo>
	 *
	 * @author EAM Santiago Gaviria Oliveros Email: sangav96@gmail.com
	 * @param data
	 * @return
	 * @date 24/02/2018
	 * @version <Version del metodo>
	 *
	 */
	private String arbolJ48(Instances data) {
		try {
			// Creamos un clasificador J48
			J48 j48 = new J48();
			// Creamos el clasificador del J48 con los datos
			j48.buildClassifier(data);

			// Creamos un objeto para la validacion del modelo con RedBayesianas
			Evaluation evalJ48 = new Evaluation(data);

			// Aplicamos el clasificador J48
			// hacemos validacion cruzada de redBayesiana con 10 campos
			// y el aleatorio arrancando desde 1 para la semilla
			evalJ48.crossValidateModel(j48, data, 10, new Random(1));

			// Obtenemos resultados
			String resJ48 = "<br><b><center>Resultados Arbol de decision J48"
					+ "</center><br><br>Modelo generado indica los " + "siguientes resultados:<br><br></b>";

			resJ48 = resJ48 + ("<b>1. Numero de instancias clasificadas:</b> " + (int) evalJ48.numInstances() + "<br>");
			resJ48 = resJ48 + ("<b>2. Porcenajte de instancias correctamente " + "clasificadas:<b/> "
					+ formato.format(evalJ48.pctCorrect()) + "%<br>");
			resJ48 = resJ48 + ("<b>3. Numero de instancias correctamente " + "clasificadas:<b/> "
					+ (int) evalJ48.pctCorrect() + "<br>");
			resJ48 = resJ48 + ("<b>4. Porcenajte de instancias incorrectamente " + "clasificadas:<b/> "
					+ formato.format(evalJ48.pctIncorrect()) + "%<br>");
			resJ48 = resJ48 + ("<b>5. Numero de instancias Incorrectamente " + "clasificadas:<b/> "
					+ (int) evalJ48.pctIncorrect() + "<br>");
			resJ48 = resJ48
					+ ("<b>6. Media del error absoluto: </b> " + formato.format(evalJ48.meanAbsoluteError()) + "%<br>");
			resJ48 = resJ48 + ("<b>7. " + evalJ48.toMatrixString("Matriz de " + "confusion</b>").replace("\n", "<br>"));
			resJ48 = resJ48 + ("<b>8. " + j48.graph() + "<br>");

			return resJ48;

		} catch (Exception e) {
			return "El error es " + e.getMessage();
		}
	}

}
