package com.creatunion.demo.db.sample;

import android.content.ContentValues;
import android.database.Cursor;

import com.creatunion.demo.db.BaseDao;
import com.creatunion.demo.db.DataSource;
import com.creatunion.demo.db.NoteBean;

/**
 * Created by ZhangZhaoCheng
 *
 * @Date: 2016/6/27
 * @version: 1.0
 * @Description:
 */
public class NoteDao extends BaseDao<NoteBean> {

    public NoteDao(DataSource dataSource) {
        super(dataSource, NoteSource.Table_Note._table_name);
    }

    @Override
    protected void setValues(NoteBean data, ContentValues cv) {
        cv.put(NoteSource.Table_Note.ID, data.getId());
        cv.put(NoteSource.Table_Note.GRAMMAR_ID, data.getGrammarId());
        cv.put(NoteSource.Table_Note.TYPE, data.getType());
        cv.put(NoteSource.Table_Note.CONTENT, data.getContent());
        cv.put(NoteSource.Table_Note.VERSION, data.getVersion());
        cv.put(NoteSource.Table_Note.LAST_OPERATE, data.getLastOperate());
        cv.put(NoteSource.Table_Note.NOTE_CREATE_TIME, data.getNoteCreateTime());
        cv.put(NoteSource.Table_Note.LOCAL_STATE, data.getLocalState());
        cv.put(NoteSource.Table_Note.UID, data.getOwnerUid());
    }

    @Override
    protected String makeDeleteWhere(NoteBean data) {
        if (data == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(NoteSource.Table_Note.NOTE_CREATE_TIME);
        sb.append("=");
        sb.append(data.getNoteCreateTime());
        return sb.toString();
    }

    public static final int index_ID = 0;
    public static final int index_GRAMMAR_ID = 1;
    public static final int index_TYPE = 2;
    public static final int index_CONTENT = 3;
    public static final int index_VERSION = 4;
    public static final int index_LAST_OPERATE = 5;
    public static final int index_NOTE_CREATE_TIME = 6;
    public static final int index_LOCAL_STATE = 7;
    public static final int index_UID = 8;
    
    @Override
    protected String makeUpdateWhere(NoteBean data) {
        return makeDeleteWhere(data);
    }

    @Override
    protected NoteBean readFullColumn(Cursor c) {
        NoteBean note = new NoteBean();
        note.setId(c.getInt(index_ID));
        note.setGrammarId(c.getInt(index_GRAMMAR_ID));
        note.setType(c.getInt(index_TYPE));
        note.setContent(c.getString(index_CONTENT));
        note.setVersion(c.getInt(index_VERSION));
        note.setLastOperate(c.getString(index_LAST_OPERATE));
        note.setNoteCreateTime(c.getLong(index_NOTE_CREATE_TIME));
        note.setLocalState(c.getInt(index_LOCAL_STATE));
        note.setOwnerUid(c.getString(index_UID));
        return note;
    }
}
