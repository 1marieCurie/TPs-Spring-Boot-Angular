package ma.ensaf.tp.pharmacy.controller;

import ma.ensaf.tp.pharmacy.dao.TagRepository;
import ma.ensaf.tp.pharmacy.model.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tags")
public class TagController {
    @Autowired
    private TagRepository tagRepository;

    @GetMapping
    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }

    // POST new tag
    @PostMapping
    public Tag createTag(@RequestBody Tag tag) {
        return tagRepository.save(tag);
    }

    // PUT update tag
    @PutMapping("/{id}")
    public Tag updateTag(@PathVariable Long id, @RequestBody Tag tag) {
        Tag existing = tagRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tag non trouvé avec id " + id));
        existing.setLibelle(tag.getLibelle());
        return tagRepository.save(existing);
    }

    // DELETE tag
    @DeleteMapping("/{id}")
    public void deleteTag(@PathVariable Long id) {
        Tag existing = tagRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tag non trouvé avec id " + id));
        tagRepository.delete(existing);
    }
}

