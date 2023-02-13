import java.util.HashMap;
import java.util.Map;

class InMemoryDatabase {
    public static final InMemoryDatabase instance = new InMemoryDatabase(); 
    private final Map<String, Object> _database;
    private InMemoryDatabase() {
        _database = new HashMap<String, Object>();
    }

    boolean contains(String key) {
        return _database.containsKey(key);
    }

    void create(String key, Object value) {
        _database.put(key, value);
    }

    Object read(String key) {
        return _database.get(key);
    }

    void update(String key, Object value) {
        create(key, value);
    }

    void delete(String key) {
        _database.remove(key);
    }
}

public interface Server {
    // SAFE HTTP METHOD
    Object GET(String path) throws HttpError;
    void POST(String path, Object body) throws HttpError;
    void PUT(String path, Object body) throws HttpError;
    void PATCH(String path, Map<String, Object> body) throws HttpError;
    void DELETE(String path) throws HttpError;
}

class HttpError extends Throwable {
    final int statusCode;

    HttpError(int statusCode) {
        this.statusCode = statusCode;
    }

    HttpError(int statusCode, String message) {
        super(statusCode + " " + message);
        this.statusCode = statusCode;
    }
}

class MyServer implements Server {
    private final InMemoryDatabase database = InMemoryDatabase.instance;

    @Override
    public Object GET(String path) throws HttpError {
        final boolean hasKey = database.contains(path);
        if (!hasKey) {
            throw new HttpError(404, "Aisa koi data hai hi nhi apun ke paas");
        }
        return database.read(path);
    }

    @Override
    public void POST(String path, Object body) throws HttpError {
        final boolean hasKey = database.contains(path);
        if (hasKey) {
            throw new HttpError(400);
        }
        database.create(path, body);
    }

    @Override
    public void PUT(String path, Object body) throws HttpError {
        final boolean hasKey = database.contains(path);
        if (!hasKey) {
            throw new HttpError(400);
        }
        database.update(path, body);
    }

    @Override
    public void PATCH(String path, Map<String, Object> body) throws HttpError {
        final boolean hasKey = database.contains(path);
        if (!hasKey) {
            throw new HttpError(400);
        }
        final Object data = database.read(path);
        if (!(data instanceof Map<?, ?>)) {
            throw new HttpError(500);
        }
        ((Map<String, Object>) data).putAll(body);
    }

    @Override
    public void DELETE(String path) throws HttpError {
        final boolean hasKey = database.contains(path);
        if (!hasKey) {
            throw new HttpError(400);
        }
        database.delete(path);
    }

}