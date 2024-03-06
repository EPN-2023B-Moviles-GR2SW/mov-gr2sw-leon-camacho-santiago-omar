package com.example.deber02_santiagoleon.models.dao

import android.content.Context
import com.example.deber02_santiagoleon.models.entities.Hotel
import com.example.deber02_santiagoleon.models.entities.Reserva
import com.google.firebase.firestore.FirebaseFirestore

class HotelDAO(context: Context?) {
    private val db = FirebaseFirestore.getInstance()
    private val collectionPath = "hoteles"
    fun getAllHotels(onSuccess: (List<Hotel>) -> Unit, onFailure: (Exception) -> Unit) {
        db.collection(collectionPath)
            .get()
            .addOnSuccessListener { result ->
                val hotels = result.toObjects(Hotel::class.java)
                onSuccess(hotels)
            }
            .addOnFailureListener { exception -> onFailure(exception) }
    }

    fun saveHotel(hotel: Hotel, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        db.collection(collectionPath)
            .add(hotel)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { exception -> onFailure(exception) }
    }

    fun updateHotel(hotelId: String, updatedFields: Map<String, Any>, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        db.collection(collectionPath).document(hotelId)
            .update(updatedFields)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { exception -> onFailure(exception) }
    }

    fun deleteHotel(hotelId: String, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        db.collection(collectionPath).document(hotelId)
            .delete()
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { exception -> onFailure(exception) }
    }

    fun findHotelById(hotelId: String, onSuccess: (Hotel?) -> Unit, onFailure: (Exception) -> Unit) {
        db.collection(collectionPath).document(hotelId)
            .get()
            .addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot.exists()) {
                    val hotel = documentSnapshot.toObject(Hotel::class.java)
                    onSuccess(hotel)
                } else {
                    onSuccess(null)
                }
            }
            .addOnFailureListener { exception -> onFailure(exception) }
    }

    fun loadReservationsForHotel(hotelId: String, onSuccess: (List<Reserva>) -> Unit, onFailure: (Exception) -> Unit) {
        db.collection(collectionPath).document(hotelId).collection("reservas")
            .get()
            .addOnSuccessListener { querySnapshot ->
                val reservas = querySnapshot.toObjects(Reserva::class.java)
                onSuccess(reservas)
            }
            .addOnFailureListener { exception ->
                onFailure(exception)
            }
    }
}