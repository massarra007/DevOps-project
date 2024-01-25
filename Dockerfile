FROM openjdk:11
EXPOSE 8087
ADD /target/Gestion_Clinique-1.0.jar Gestion_Clinique.jar
# ADD http://20.199.109.158:8081/service/rest/v1/search/assets/download?sort=version&repository=devops&maven.groupId=tn.esprit.spring&maven.artifactId=gestion-station-ski&maven.extension=jar ski-piste.jar
ENTRYPOINT ["java","-jar","/Gestion_Clinique.jar"]
