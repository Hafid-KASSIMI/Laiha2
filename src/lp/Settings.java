

package lp;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentHashMap;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lp.models.templates.TemplateInfos;
import net.mdrassty.database.Preferences;
import net.mdrassty.massar.DBWorkbook;
import net.mdrassty.object.School;
import net.mdrassty.util.EnvVariable;


public class Settings {
    public static final String DB_FOLDER_PATH = EnvVariable.APPDATADirectory() + "/Laiha/";
    public static final String EXTENSION = ".200.la";
    public static final String PREF_DB_NAME = "preferences" + EXTENSION;
    public static final String I18N_DB_NAME = "properties" + EXTENSION;
    public static final String PREF_DB_PATH = DB_FOLDER_PATH + PREF_DB_NAME;
    public static final String I18N_DB_PATH = DB_FOLDER_PATH + I18N_DB_NAME;
    public static final String APP_NAME = "Laiha";
    public static final String APP_VERSION = "2.0.0";
    public static final String APP_YEAR = "2022";
    public static final String APP_DATE = "2022-05-12";
    public static final String APP_TITLE = APP_NAME + " " + APP_VERSION;
    public static final ArrayList<String> SUPPORTED_LANGS = new ArrayList(Arrays.asList("AR", "FR", "EN"));
    public static String SELECTED_LANG;
    
    public static ResourceBundle I18N_BUNDLE;
    public static Preferences PREF_BUNDLE, PREF_TEMPORARY;
    public static final ConcurrentHashMap<Integer, School> DATABASES = new ConcurrentHashMap();
    public static final ObservableList<Integer> SELECTED_TEMPLATES = FXCollections.observableArrayList();
    public static ArrayList<Integer> SELECTED_TEMPLATES_TEMPORARY;
    public static DBWorkbook MASSAR_WORKBOOK;
    public static HashMap<Integer, TemplateInfos> AVAILABLE_TEMPLATES = new HashMap();
    
    public static boolean quitAuthorized = false;

}
