
interface props{
     estadoBotao : boolean
}

export default function ListCard({estadoBotao = true}: props) {
    return (
            <tbody>
                <tr>
                    <th scope="row"><button hidden={estadoBotao} type="button" className="btnPersonal"> Apagar</button> 
                    </th>
                    <td>Felipe </td>
                    <td>123456 </td>
                    <td>Pão </td>
                </tr>
            </tbody>
    )
}