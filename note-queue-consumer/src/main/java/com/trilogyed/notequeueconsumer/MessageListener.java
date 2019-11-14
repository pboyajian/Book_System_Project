package com.trilogyed.notequeueconsumer;

import com.trilogyed.notequeueconsumer.util.feign.NoteServerClient;
import com.trilogyed.notequeueconsumer.util.messages.Note;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class MessageListener {
@Autowired
private NoteServerClient noteServerClient;
    @RabbitListener(queues = NoteQueueConsumerApplication.QUEUE_NAME)
    public void receiveMessage(Note msg) {
        try{
            Note note=noteServerClient.getNote(msg.getNoteId());
            noteServerClient.updateNote(note);
        }catch(EmptyResultDataAccessException erdae){
            noteServerClient.addNote(msg);
        }
    }
}
