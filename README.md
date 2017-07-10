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
In order to run the project a user in the MySQL database is required with: <br>
<code>CREATE USER 'gaming'@'localhost' IDENTIFIED BY 'gaming';</code> <br>
<code>GRANT ALL PRIVILEGES ON * . * TO 'gaming'@'localhost';</code> <br>
Then: <br>
<ol>
<li>Clone this repo <code>git clone https://github.com/LuigiCerone/GameProject.git</code></li>
<li><code>cd</code> into the folder cloned</li>
<li><code>mysql -u gaming -p src/gaming.sql</code></li>
<li>Launch the app either with <code>java -jar src/GamingPlatform.jar</code> or by using GUI</ol>

<h3>Contacts</h3>
<ul>
<li>luigiceroneaq@gmail.com </li>
<li>taglieri96@gmail.com </li>
<li>danilorosati96@gmail.com </li>
</ul>

