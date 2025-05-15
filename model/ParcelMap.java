package model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


public class ParcelMap {
    private Map<String, Parcel> parcelMap;

    public ParcelMap() {
        parcelMap = new HashMap<>();
    }

    public void addParcel(Parcel parcel) {
        parcelMap.put(parcel.getParcelId(), parcel);
        Log.getInstance().logEvent("Parcel added: " + parcel.getParcelId());
    }

    
   
    public Parcel getParcel(String parcelId) {
        return parcelMap.get(parcelId);
    }

 
    public void removeParcel(String parcelId) {
        if (parcelMap.containsKey(parcelId)) {
            parcelMap.remove(parcelId);
            Log.getInstance().logEvent("Parcel removed: " + parcelId);
        } else {
            Log.getInstance().logEvent("Attempted to remove non-existent parcel: " + parcelId);
        }
    }

   
    public Collection<Parcel> getAllParcels() {
        return parcelMap.values();
    }

    public boolean isEmpty() {
        return parcelMap.isEmpty();
    }
}
