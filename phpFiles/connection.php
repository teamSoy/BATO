<?php
	define('hostname','localhost');
	define('username','root');
	define('password','linux');
	define('databasename','teasoy');
	
	class DB_Connection
	{
		private $connect;
		function __construct(){
			$this->connect = mysqli_connect(hostname,username,password,databasename) or die ("DB Connection Error");
		}
		public function get_connection()
		{
			return $this->connect;
		}
	}

?>