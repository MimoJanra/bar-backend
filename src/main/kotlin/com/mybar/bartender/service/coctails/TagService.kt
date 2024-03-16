package com.mybar.bartender.service.coctails

import com.mybar.bartender.model.cocktails.Tag
import com.mybar.bartender.repository.cocktails.TagRepository
import org.springframework.stereotype.Service

@Service
class TagService(private val tagRepository: TagRepository) {

    fun findAllTags(): List<Tag> = tagRepository.findAll()

    fun findTagById(id: Long): Tag? = tagRepository.findById(id).orElse(null)

    fun createTag(tag: Tag): Tag = tagRepository.save(tag)

    fun updateTag(id: Long, updatedTag: Tag): Tag? {
        return if (tagRepository.existsById(id)) {
            tagRepository.save(updatedTag)
        } else null
    }

    fun deleteTag(id: Long) {
        tagRepository.deleteById(id)
    }
}