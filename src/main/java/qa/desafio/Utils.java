package qa.desafio;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.junit.Assert;

import java.util.Random;


public class Utils {

    public static PropertiesConfiguration getProperties(String propertiesPath){
        try {
            return new PropertiesConfiguration(propertiesPath);
        } catch (ConfigurationException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int gerarNumeroRandom(int limiteInicial, int limiteFinal){
        if(limiteInicial > limiteFinal){
            Assert.fail("O limite inicial não pode ser superior ao limite final.");
        }

        Random rand = new Random();
        return rand.nextInt((limiteFinal - limiteInicial) + 1) + limiteInicial;
    }

    public static String gerarLetrasAleatorias(int tamanhoFinal){
        final String alphabet = "abcdefghijklmnopqrstuvwxyz";
        final int N = alphabet.length();
        Random rd = new Random();
        StringBuilder sb = new StringBuilder(tamanhoFinal);
        for (int i = 0; i < tamanhoFinal; i++) {
            sb.append(alphabet.charAt(rd.nextInt(N)));
        }

        return sb.toString();
    }
}
