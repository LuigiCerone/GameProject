# GameProject

<h2>Java gaming platform, OOP course project 2016/17</h2>
<hr>
<h3>Team</h3>
<ul>
<li>Taglieri Alessandro 243488 
<li>Rosati Danilo 243748
<li>Cerone Luigi 242895
</ul>

<h3>Repository structure</h3>

<b>`src`</b> : folder for project's source code <br>
<b>`doc`</b> : folder for project's documentation <br>
<b>`javadoc`</b> : folder for project's javadoc files <br>
<b>`src/img`</b> : folder for trophies' images <br>


<h3>Installation</h3>
In order to run the project a user in the MySQL database is required with 
`CREATE USER 'gaming'@'localhost' IDENTIFIED BY 'gaming';`
`GRANT ALL PRIVILEGES ON * . * TO 'gaming'@'localhost';`
Then
<ol>
<li>Clone this repo <code>git clone https://github.com/LuigiCerone/GameProject.git</code></li>
<li><code>cd</code> into the folder cloned</li>
<li><code>mysql -u gaming -p src/gaming.sql</code></li>
<li>Launch the app either with <code>java -jar src/GamingPlatform.jar</code> or via GUI</ol>

<h3>Contacts</h3>
luigiceroneaq@gmail.com <br>
taglieri96@gmail.com <br>
danilorosati96@gmail.com <br>


