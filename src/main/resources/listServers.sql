CREATE OR REPLACE FUNCTION list_servers()
    RETURNS TABLE (id VARCHAR(48), name TEXT, description TEXT) AS $$
BEGIN

return query
SELECT * FROM servers;

END
$$ LANGUAGE plpgsql