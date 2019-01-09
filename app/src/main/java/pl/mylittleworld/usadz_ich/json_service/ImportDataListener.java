package pl.mylittleworld.usadz_ich.json_service;

/**
 * Listener which waits for database data access
 */
public interface ImportDataListener {

   /**
    * This method is invoked when data is ready
    */
   void onDataImported();
}
