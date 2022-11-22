## Endpoints list
prefix: "/list"
# HEADERS: 
        -Authorization: token (application.properties: BEARER_TOKEN)
# REST
Method: GET
Endpoint: "/show"
Params: -
Body: -
Get collection of Lists 

Method: POST
Endpoint: "/create"
Params: -
Body: name:string, songs:[]
Create a new List with their songs 

Method: PUT
Endpoint: "/edit"
Params: {:id}:int
Body: name:string?, songs:[]?
Get a song using their ID and then edits it if necessary 

Method: DELETE
Endpoint: "/delete"
Params: {:id}:int
Body: -
Get a song using their ID and then deletes it

Method: GET
Endpoint: "/search-lists"
Params: {:song_title}:string
Body: -
Search a list by song title and returns all matching lists