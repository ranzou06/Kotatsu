package org.koitharu.kotatsu.core.backup

import org.json.JSONObject
import org.koitharu.kotatsu.core.db.entity.MangaEntity
import org.koitharu.kotatsu.core.db.entity.TagEntity
import org.koitharu.kotatsu.favourites.data.FavouriteCategoryEntity
import org.koitharu.kotatsu.favourites.data.FavouriteEntity
import org.koitharu.kotatsu.history.data.HistoryEntity
import org.koitharu.kotatsu.parsers.model.SortOrder
import org.koitharu.kotatsu.parsers.util.json.getBooleanOrDefault
import org.koitharu.kotatsu.parsers.util.json.getFloatOrDefault
import org.koitharu.kotatsu.parsers.util.json.getStringOrNull

class JsonDeserializer(private val json: JSONObject) {

	fun toFavouriteEntity() = FavouriteEntity(
		mangaId = json.getLong("manga_id"),
		categoryId = json.getLong("category_id"),
		createdAt = json.getLong("created_at"),
	)

	fun toMangaEntity() = MangaEntity(
		id = json.getLong("id"),
		title = json.getString("title"),
		altTitle = json.getStringOrNull("alt_title"),
		url = json.getString("url"),
		publicUrl = json.getStringOrNull("public_url").orEmpty(),
		rating = json.getDouble("rating").toFloat(),
		isNsfw = json.getBooleanOrDefault("nsfw", false),
		coverUrl = json.getString("cover_url"),
		largeCoverUrl = json.getStringOrNull("large_cover_url"),
		state = json.getStringOrNull("state"),
		author = json.getStringOrNull("author"),
		source = json.getString("source")
	)

	fun toTagEntity() = TagEntity(
		id = json.getLong("id"),
		title = json.getString("title"),
		key = json.getString("key"),
		source = json.getString("source")
	)

	fun toHistoryEntity() = HistoryEntity(
		mangaId = json.getLong("manga_id"),
		createdAt = json.getLong("created_at"),
		updatedAt = json.getLong("updated_at"),
		chapterId = json.getLong("chapter_id"),
		page = json.getInt("page"),
		scroll = json.getDouble("scroll").toFloat(),
		percent = json.getFloatOrDefault("percent", -1f),
	)

	fun toFavouriteCategoryEntity() = FavouriteCategoryEntity(
		categoryId = json.getInt("category_id"),
		createdAt = json.getLong("created_at"),
		sortKey = json.getInt("sort_key"),
		title = json.getString("title"),
		order = json.getStringOrNull("order") ?: SortOrder.NEWEST.name,
		track = json.getBooleanOrDefault("track", true),
	)
}