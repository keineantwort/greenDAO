package de.greenrobot.daotest;

import java.util.List;
import java.util.ArrayList;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.SqlUtils;
import de.greenrobot.dao.internal.DaoConfig;

import de.greenrobot.daotest.RelationEntity;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table RELATION_ENTITY.
*/
public class RelationEntityDao extends AbstractDao<RelationEntity, Long> {

    public static final String TABLENAME = "RELATION_ENTITY";

    /**
     * Properties of entity RelationEntity.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property ParentId = new Property(1, Long.class, "parentId", false, "PARENT_ID");
        public final static Property TestId = new Property(2, Long.class, "testId", false, "TEST_ID");
        public final static Property TestIdNotNull = new Property(3, long.class, "testIdNotNull", false, "TEST_ID_NOT_NULL");
        public final static Property SimpleString = new Property(4, String.class, "simpleString", false, "SIMPLE_STRING");
        public final static Property TestWithoutProperty = new Property(5, Long.class, "testWithoutProperty", false, "WITHOUT_PROPERTY_TEST_ID");
    };

    private DaoSession daoSession;


    public RelationEntityDao(DaoConfig config) {
        super(config);
    }
    
    public RelationEntityDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'RELATION_ENTITY' (" + //
                "'_id' INTEGER PRIMARY KEY ," + // 0: id
                "'PARENT_ID' INTEGER," + // 1: parentId
                "'TEST_ID' INTEGER," + // 2: testId
                "'TEST_ID_NOT_NULL' INTEGER NOT NULL ," + // 3: testIdNotNull
                "'SIMPLE_STRING' TEXT," + // 4: simpleString
                "'WITHOUT_PROPERTY_TEST_ID' INTEGER);"); // 5: testWithoutProperty
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'RELATION_ENTITY'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, RelationEntity entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Long parentId = entity.getParentId();
        if (parentId != null) {
            stmt.bindLong(2, parentId);
        }
 
        Long testId = entity.getTestId();
        if (testId != null) {
            stmt.bindLong(3, testId);
        }
        stmt.bindLong(4, entity.getTestIdNotNull());
 
        String simpleString = entity.getSimpleString();
        if (simpleString != null) {
            stmt.bindString(5, simpleString);
        }
    }

    @Override
    protected void attachEntity(RelationEntity entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public RelationEntity readEntity(Cursor cursor, int offset) {
        RelationEntity entity = new RelationEntity( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1), // parentId
            cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2), // testId
            cursor.getLong(offset + 3), // testIdNotNull
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4) // simpleString
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, RelationEntity entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setParentId(cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1));
        entity.setTestId(cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2));
        entity.setTestIdNotNull(cursor.getLong(offset + 3));
        entity.setSimpleString(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(RelationEntity entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(RelationEntity entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
    private String selectDeep;

    protected String getSelectDeep() {
        if (selectDeep == null) {
            StringBuilder builder = new StringBuilder("SELECT ");
            SqlUtils.appendColumns(builder, "T", getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T0", daoSession.getRelationEntityDao().getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T1", daoSession.getTestEntityDao().getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T2", daoSession.getTestEntityDao().getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T3", daoSession.getTestEntityDao().getAllColumns());
            builder.append(" FROM RELATION_ENTITY T");
            builder.append(" LEFT JOIN RELATION_ENTITY T0 ON T.'PARENT_ID'=T0.'_id'");
            builder.append(" LEFT JOIN TEST_ENTITY T1 ON T.'TEST_ID'=T1.'_id'");
            builder.append(" LEFT JOIN TEST_ENTITY T2 ON T.'TEST_ID_NOT_NULL'=T2.'_id'");
            builder.append(" LEFT JOIN TEST_ENTITY T3 ON T.'WITHOUT_PROPERTY_TEST_ID'=T3.'_id'");
            builder.append(' ');
            selectDeep = builder.toString();
        }
        return selectDeep;
    }
    
    protected RelationEntity loadCurrentDeep(Cursor cursor, boolean lock) {
        RelationEntity entity = loadCurrent(cursor, 0, lock);
        int offset = getAllColumns().length;

        RelationEntity parent = loadCurrentOther(daoSession.getRelationEntityDao(), cursor, offset);
        entity.setParent(parent);
        offset += daoSession.getRelationEntityDao().getAllColumns().length;

        TestEntity testEntity = loadCurrentOther(daoSession.getTestEntityDao(), cursor, offset);
        entity.setTestEntity(testEntity);
        offset += daoSession.getTestEntityDao().getAllColumns().length;

        TestEntity testNotNull = loadCurrentOther(daoSession.getTestEntityDao(), cursor, offset);
         if(testNotNull != null) {
            entity.setTestNotNull(testNotNull);
        }
        offset += daoSession.getTestEntityDao().getAllColumns().length;

        TestEntity testWithoutProperty = loadCurrentOther(daoSession.getTestEntityDao(), cursor, offset);
        entity.setTestWithoutProperty(testWithoutProperty);

        return entity;    
    }

    public RelationEntity loadDeep(Long key) {
        assertSinglePk();
        if (key == null) {
            return null;
        }

        StringBuilder builder = new StringBuilder(getSelectDeep());
        builder.append("WHERE ");
        SqlUtils.appendColumnsEqValue(builder, "T", getPkColumns());
        String sql = builder.toString();
        
        String[] keyArray = new String[] { key.toString() };
        Cursor cursor = db.rawQuery(sql, keyArray);
        
        try {
            boolean available = cursor.moveToFirst();
            if (!available) {
                return null;
            } else if (!cursor.isLast()) {
                throw new IllegalStateException("Expected unique result, but count was " + cursor.getCount());
            }
            return loadCurrentDeep(cursor, true);
        } finally {
            cursor.close();
        }
    }
    
    /** Reads all available rows from the given cursor and returns a list of new ImageTO objects. */
    public List<RelationEntity> loadAllDeepFromCursor(Cursor cursor) {
        int count = cursor.getCount();
        List<RelationEntity> list = new ArrayList<RelationEntity>(count);
        
        if (cursor.moveToFirst()) {
            if (identityScope != null) {
                identityScope.lock();
                identityScope.reserveRoom(count);
            }
            try {
                do {
                    list.add(loadCurrentDeep(cursor, false));
                } while (cursor.moveToNext());
            } finally {
                if (identityScope != null) {
                    identityScope.unlock();
                }
            }
        }
        return list;
    }
    
    protected List<RelationEntity> loadDeepAllAndCloseCursor(Cursor cursor) {
        try {
            return loadAllDeepFromCursor(cursor);
        } finally {
            cursor.close();
        }
    }
    

    /** A raw-style query where you can pass any WHERE clause and arguments. */
    public List<RelationEntity> queryDeep(String where, String... selectionArg) {
        Cursor cursor = db.rawQuery(getSelectDeep() + where, selectionArg);
        return loadDeepAllAndCloseCursor(cursor);
    }
 
}
