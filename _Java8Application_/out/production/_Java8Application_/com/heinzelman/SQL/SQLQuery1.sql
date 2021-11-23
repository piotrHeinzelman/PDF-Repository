


  CREATE TABLE projects ( id INT, name VARCHAR(255), active BIT , version INT );


CREATE OR ALTER FUNCTION getAll ()
RETURNS TABLE
AS
RETURN 
(
	SELECT * FROM projects
);


 SELECT * FROM getAll();






CREATE OR ALTER FUNCTION insertProj ( @projName VARCHAR(255) )
RETURNS TABLE
AS
RETURN 
(
	
	--INSERT INTO projects

	SELECT * FROM projects WHERE name=@projName
);


 SELECT * FROM insertProj( 'abc' );







  /*

CREATE OR ALTER  FUNCTION proj ( @codeId BIGINT )
RETURNS TABLE
AS
RETURN 
(

SELECT 
	   CO.code_id AS code_id, 
	   NULL AS tparent,
	   null AS lparent,
	   null AS ord
	   FROM code AS CO WHERE CO.code_id = @codeId

UNION
	SELECT 
	   CT.children_id AS code_id, 
	   CT.parent_id AS tparent,
	   null AS lparent,
	   null AS ord
	   FROM children_in_table AS CT WHERE CT.parent_id = @codeId
UNION
	SELECT 
	   CI.children_id AS code_id, 
	   NULL AS tparent,
	   CI.parent_id AS lparent,
	   null AS ord
	   FROM children_inline AS CI WHERE CI.parent_id = @codeId

);
GO


-- usage SELECT * FROM codes_family ( 104 )


*/