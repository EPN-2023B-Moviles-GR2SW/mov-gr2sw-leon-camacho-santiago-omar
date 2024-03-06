package com.example.deber02_santiagoleon.models.dao

import android.content.ContentValues
import android.content.Context
import com.example.deber02_santiagoleon.models.entities.Reserva
import com.google.firebase.firestore.FirebaseFirestore

class ReservaDAO(context: Context?) {
    private val db = FirebaseFirestore.getInstance()
    private val hotelsCollectionPath = "hoteles"

    fun getAllReservas(onSuccess: (List<Reserva>) -> Unit, onFailure: (Exception) -> Unit) {
        db.collectionGroup("reservas")
            .get()
            .addOnSuccessListener { querySnapshot ->
                val reservas = querySnapshot.toObjects(Reserva::class.java)
                onSuccess(reservas)
            }
            .addOnFailureListener { exception ->
                onFailure(exception)
            }
    }

    fun saveReserva(hotelId: String, reserva: Reserva, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        db.collection(hotelsCollectionPath).document(hotelId).collection("reservas")
            .add(reserva)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { exception -> onFailure(exception) }
    }

    fun updateReserva(hotelId: String, reservaId: String, updatedFields: Map<String, Any>, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        db.collection(hotelsCollectionPath).document(hotelId).collection("reservas").document(reservaId)
            .update(updatedFields)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { exception -> onFailure(exception) }
    }

    fun deleteReserva(hotelId: String, reservaId: String, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        db.collection(hotelsCollectionPath).document(hotelId).collection("reservas").document(reservaId)
            .delete()
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { exception -> onFailure(exception) }
    }

    fun findReservaById(hotelId: String, reservaId: String, onSuccess: (Reserva?) -> Unit, onFailure: (Exception) -> Unit) {
        db.collection(hotelsCollectionPath).document(hotelId).collection("reservas").document(reservaId)
            .get()
            .addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot.exists()) {
                    val reserva = documentSnapshot.toObject(Reserva::class.java)
                    onSuccess(reserva)
                } else {
                    onSuccess(null)
                }
            }
            .addOnFailureListener { exception -> onFailure(exception) }
    }

    fun getReservationsByHotelId(hotelId: String, onSuccess: (List<Reserva>) -> Unit, onFailure: (Exception) -> Unit) {
        db.collection(hotelsCollectionPath).document(hotelId).collection("reservas")
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