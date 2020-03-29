package org.koitharu.kotatsu.core.db

import androidx.room.*
import org.koitharu.kotatsu.core.db.entity.TrackEntity


@Dao
abstract class TracksDao {

	@Query("SELECT * FROM tracks")
	abstract suspend fun findAll(): List<TrackEntity>

	@Query("DELETE FROM tracks")
	abstract suspend fun clear()

	@Insert(onConflict = OnConflictStrategy.IGNORE)
	abstract suspend fun insert(entity: TrackEntity): Long

	@Update
	abstract suspend fun update(entity: TrackEntity): Int

	@Query("DELETE FROM tracks WHERE manga_id = :mangaId")
	abstract suspend fun delete(mangaId: Long)

	@Transaction
	open suspend fun upsert(entity: TrackEntity) {
		if (update(entity) == 0) {
			insert(entity)
		}
	}

}