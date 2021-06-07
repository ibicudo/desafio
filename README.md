# desafio

Arquivos json:
- O Arquivo users.json já possui 20 users criados , sendo que do 1 ao 10 são apenas users e do 11 ao 20 são sellers 

Para rodar, apenas starter a aplicação e rodar as collections do postman que estão nomeadas conforme os requerimentos definidos.
No Postman temos:
 US 0001-  followSeller():  
	- já temos alguns usuários seguindo alguns vendedores (consegue verificar no json), então ao tentar seguir um vendedor 	novamente ele vai trazer uma exception. 
	- Lembrando que os users vão  do ID 1 ao 10 porque já estão cadastrados e os sellers que podem ser seguidos vão de 11 a	 20
	- Não é permitido um vendedor seguir ele mesmo 

US 0002 - getTotalFollowers(): 
	- Passar um ID de 11 a 20 (vendedores cadastrados)
	- Vendedores que já tem seguidores : 11, 12, 15, 20


US 0003 - getListFollowers ():
	-Passar um ID de 11 a 20 
	- Vendedores que já tem seguidores : 11, 12, 15, 20

US 0004	- getFollowedList()
	- Passar um ID de 1 a 20
	-Usuários ou vendedora que já estão seguindo algum vendedor da lista: 1, 2, 3, 4, 9, 11
	-Os outros usuários não estão seguindo nenhum vendedor por enquanto, logo a lista virá vazia

US 0005 - createPost():
	- userId tem que ser de 11 a 20
	-userId já tem 2 posts criados
	-Os posts estão sendo criados no post.json

US 0006 - getPostsLastTwoWeeks():
	-Passar um ID de 1 a 20
	-O user 3 já está seguindo vendedores que fizeram publicação de algum post

US 0007 - unfollowSeller():
	-Passar ID de 1 a 20
	- Users que já estão seguindo algum vendedor na base atual:
		1 está seguindo 20
		2 está seguindo 11, 12
		3 está seguindo 11, 15
		4 está seguindo 11
		9 está seguindo 11
		11 está seguindo 20

US 0008 - getFollowersASC
	-Retorna lista de seguidores ordenado de forma crescente
	-Passar ID de 11 a 20 

US 0008 - getFollowersDESC
	-Retorna lista de seguidores ordenado de forma decrescente
	-Passar ID de 11 a 20 

US 0008 - getFollowedASC
	-Retorna lista de vendedores que um usuário segue ordenado de forma crescente
	-Passar ID de 1 a 20 

US 0008 - getFollowedDESC
	-Retorna lista de vendedores que um usuário segue ordenado de forma decrescente
	-Passar ID de 1 a 20 

US 0009 - getPostsASC:
	-Passar a ID de 1 a 20 
	- Na base , o id 3 já está seguindo vendedor que tem publicação de posts , os que não ao estiverem vai retornar vazio

US 0009 - getPostsDESC
	-Passar a ID de 1 a 20 
	- Na base , o id 3 já está seguindo vendedor que tem publicação de posts , os que não ao estiverem vai retornar vazio

US 0010 - createPromoPost
	- Passar payload conforme já está lá
	- Na base, já tem um post cadastrado para o userId 11 e dois para o userId 12

US 0011 - countPromo
	-Passar ID de 11 a 20 
	-Somente o ID 11 e 12 tem posts promocionais

US 0012 - getListPromoPostBySeller
	-Passar ID de 11 a 20
	-Somente o ID 11 e 12 tem posts promocionais


Arquivos (estão em resources): 
post.json :  armazena os posts criados
postPromo.json : armazena os posts promocionais criados
sellers.json: armazena os vendedores
users.json: armazena todos os usuários (incluindo os vendedores que estão em sellers, sendo que tem o mesmo id em ambos arquivos)
