import styles from './App.css'

const isAuth = true;

const MENU = [
	{
		name: 'Home',
		link: '/'
	},
	{
		name: 'List of Matrix',
		link: '/mlist'
	},
	{
		name: 'Matrix',
		link: '/matrix'
	}
]

export function App() {
	return (
		<div className={styles.layout}>

			{MENU.map(item => (
				<p key={item.link}>{item.name}</p>
			))}
			<h1>First component</h1>
		</div>
	);
}