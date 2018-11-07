rd /S/Q C:\Programas\Servidores\jboss-5.1.0.GA\server\cronos-server\deploy\cronos.war\
mkdir C:\Programas\Servidores\jboss-5.1.0.GA\server\cronos-server\deploy\cronos.war\
xcopy /E C:\Ambiente\eclipse-indigo\Workspace\Cronos-2015-Enxuto\client-web-war\target\cronos\*.* C:\Programas\Servidores\jboss-5.1.0.GA\server\cronos-server\deploy\cronos.war\
xcopy /E C:\Users\CT-Redes\.m2\repository\org\springframework\spring-web\3.2.9.RELEASE\*.* C:\Programas\Servidores\jboss-5.1.0.GA\server\cronos-server\deploy\cronos.war\WEB-INF\lib\