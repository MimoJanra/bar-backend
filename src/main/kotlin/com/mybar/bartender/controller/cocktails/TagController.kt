package com.mybar.bartender.controller.cocktails

import com.mybar.bartender.model.cocktails.Tag
import com.mybar.bartender.service.coctails.TagService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/tags")
class TagController(private val tagService: TagService) {

    @GetMapping
    fun getAllTags(): ResponseEntity<List<Tag>> = ResponseEntity.ok(tagService.findAllTags())

    @PostMapping
    fun createTag(@RequestBody tag: Tag): ResponseEntity<Tag> = ResponseEntity.ok(tagService.createTag(tag))

    @PutMapping("/{id}")
    fun updateTag(@PathVariable id: Long, @RequestBody tag: Tag): ResponseEntity<Tag> =
        tagService.updateTag(id, tag)?.let { ResponseEntity.ok(it) } ?: ResponseEntity.notFound().build()

    @DeleteMapping("/{id}")
    fun deleteTag(@PathVariable id: Long): ResponseEntity<Void> {
        tagService.deleteTag(id)
        return ResponseEntity.ok().build()
    }
}