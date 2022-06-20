package UiResources.Pages.BasePage;

public class BasePage {
    public static String searchQuery = null;
    public static String firstResultURL = null;

    public static void setFinderQuery(String query) {
        searchQuery = query;
    }

    public static void setFinderURL(String query) {
        firstResultURL = query;
    }
}
