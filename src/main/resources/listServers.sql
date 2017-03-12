CREATE OR REPLACE FUNCTION list_servers()
    RETURNS TABLE (id integer, name VARCHAR(20), description TEXT) AS $$
BEGIN

return query
SELECT * FROM servers;

END
$$ LANGUAGE plpgsql