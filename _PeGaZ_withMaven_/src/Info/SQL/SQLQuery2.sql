

CREATE OR ALTER  PROCEDURE  addProj(
        @projName VARCHAR(50)
		)

AS  
	INSERT INTO dbo.projects (  [name], [active] )VALUES (   @projName, 1 )
		
GO


 
DECLARE @name varchar(50)
SET @name='abc';

exec dbo.addProj  @name 