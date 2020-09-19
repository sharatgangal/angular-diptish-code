package com.stackroute.keepnote.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.keepnote.exception.NoteNotFoundExeption;
import com.stackroute.keepnote.model.Note;
import com.stackroute.keepnote.model.NoteUser;
import com.stackroute.keepnote.repository.NoteRepository;

/*
* Service classes are used here to implement additional business logic/validation 
* This class has to be annotated with @Service annotation.
* @Service - It is a specialization of the component annotation. It doesn't currently 
* provide any additional behavior over the @Component annotation, but it's a good idea 
* to use @Service over @Component in service-layer classes because it specifies intent 
* better. Additionally, tool support and additional behavior might rely on it in the 
* future.
* */
@Service
public class NoteServiceImpl implements NoteService {

	/*
	 * Autowiring should be implemented for the NoteRepository and MongoOperation.
	 * (Use Constructor-based autowiring) Please note that we should not create any
	 * object using the new keyword.
	 */
	@Autowired
	NoteRepository noteRepository;

	public NoteServiceImpl(NoteRepository noteRepository) {
		super();
		this.noteRepository = noteRepository;
	}

	/*
	 * This method should be used to save a new note.
	 */
	public boolean createNote(Note note) {

		List<Note> noteList = new ArrayList<>();
		NoteUser noteUser = new NoteUser();
		noteList.add(note);
		noteUser.setNotes(noteList);
		noteUser.setUserId(note.getNoteCreatedBy());
		if (noteRepository.insert(noteUser) != null) {
			return true;
		} else {
			return false;
		}

	}
	/* This method should be used to delete an existing note. */

	public boolean deleteNote(String userId, int noteId) {

		Optional<NoteUser> noteUser = noteRepository.findById(userId);
		if (noteUser.isPresent()) {
			noteRepository.delete(noteUser.get());
			return true;
		} else {
			return false;
		}
	}

	/* This method should be used to delete all notes with specific userId. */

	public boolean deleteAllNotes(String userId) {

		Optional<NoteUser> noteList = noteRepository.findById(userId);
		NoteUser noteUser = noteList.get();
		if (noteUser.getNotes() != null) {
			noteUser.setNotes(noteUser.getNotes().stream().filter(n -> !(n.getNoteCreatedBy().equals(userId)))
					.collect(Collectors.toList()));
			noteRepository.save(noteUser);
			return true;
		}
		return false;
	}

	/*
	 * This method should be used to update a existing note.
	 */
	public Note updateNote(Note note, int id, String userId) throws NoteNotFoundExeption {
		try {
			Optional<NoteUser> optionalNoteUser = noteRepository.findById(userId);
			NoteUser noteUser = optionalNoteUser.get();
			if (noteUser.getNotes() != null) {
				List<Note> updatedNote = new ArrayList<>();
				noteUser.getNotes().stream().forEach(n -> {
					if (n.getNoteId() == id) {
						updatedNote.add(note);
					} else {
						updatedNote.add(n);
					}
				});
				noteUser.setNotes(updatedNote);
				noteRepository.save(noteUser);

			}
		} catch (NoSuchElementException exception) {
			throw new NoteNotFoundExeption("NoteNotFoundExeption");
		}
		return note;
	}

	/*
	 * This method should be used to get a note by noteId created by specific user
	 */
	public Note getNoteByNoteId(String userId, int noteId) throws NoteNotFoundExeption {
		Note note = null;
		try {
			NoteUser noteUser = noteRepository.findById(userId).get();
			List<Note> noteList = noteUser.getNotes();
			if (noteList != null) {
				note = noteList.stream().filter(n -> n.getNoteId() == noteId).findFirst().get();
			}
		} catch (NoSuchElementException exception) {
			throw new NoteNotFoundExeption("NoteNotFoundExeption");
		}
		return note;
	}

	/*
	 * This method should be used to get all notes with specific userId.
	 */
	public List<Note> getAllNoteByUserId(String userId) {

		return noteRepository.findById(userId).get().getNotes();
	}

}
