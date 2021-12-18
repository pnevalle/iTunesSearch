package com.pnevalle.itunessearch.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.pnevalle.itunessearch.common.LAST_NETWORK_CALL_DATA_STORE_NAME
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

/**
 * The class for managing the last network call data store
 */
@Singleton
class LastNetworkCallDataStore @Inject constructor(@ApplicationContext private val context: Context) {

    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = LAST_NETWORK_CALL_DATA_STORE_NAME)
        private val LAST_NETWORK_CALL_KEY = longPreferencesKey("last_network_call_key")
    }

    val lastNetworkCallFlow: Flow<Long> = context.dataStore.data
        .catch { throwable ->
            if (throwable is IOException) {
                emit(emptyPreferences())
            } else {
                throw throwable
            }
        }
        .map { preference ->
            preference[LAST_NETWORK_CALL_KEY] ?: 0
        }

    /**
     * Save the last network call time stamp
     *
     * @param timeInMillis the timestamp to save
     */
    suspend fun saveLastNetworkCall(timeInMillis: Long) {
        context.dataStore.edit { preferences ->
            preferences[LAST_NETWORK_CALL_KEY] = timeInMillis
        }
    }
}