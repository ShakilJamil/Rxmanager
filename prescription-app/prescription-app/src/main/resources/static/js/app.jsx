const { useState, useEffect } = React;

// Component for your own prescriptions
function PrescriptionList() {
    const [prescriptions, setPrescriptions] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        fetch('/api/v1/prescription')
            .then(response => response.json())
            .then(data => {
                setPrescriptions(data);
                setLoading(false);
            })
            .catch(err => {
                console.error('Error fetching prescriptions:', err);
                setError('Failed to load prescriptions.');
                setLoading(false);
            });
    }, []);

    if (loading) return <p className="p-4">Loading prescriptions...</p>;
    if (error) return <p className="p-4 text-red-500">{error}</p>;

    return (
        <div className="container mx-auto p-4">
            <h2 className="text-2xl mb-4 font-semibold">Prescription List</h2>
            <div className="overflow-x-auto">
                {prescriptions.length === 0 ? (
                    <p>No prescriptions found.</p>
                ) : (
                    <table className="w-full border">
                        <thead>
                            <tr className="bg-gray-200">
                                <th className="p-2 border">Date</th>
                                <th className="p-2 border">Patient Name</th>
                                <th className="p-2 border">Age</th>
                                <th className="p-2 border">Gender</th>
                                <th className="p-2 border">Diagnosis</th>
                                <th className="p-2 border">Medicines</th>
                                <th className="p-2 border">Next Visit</th>
                            </tr>
                        </thead>
                        <tbody>
                            {prescriptions.map(p => (
                                <tr key={p.id} className="hover:bg-gray-100">
                                    <td className="p-2 border">{p.prescriptionDate}</td>
                                    <td className="p-2 border">{p.patientName}</td>
                                    <td className="p-2 border">{p.patientAge}</td>
                                    <td className="p-2 border">{p.patientGender}</td>
                                    <td className="p-2 border">{p.diagnosis}</td>
                                    <td className="p-2 border">{p.medicines}</td>
                                    <td className="p-2 border">{p.nextVisitDate || '-'}</td>
                                </tr>
                            ))}
                        </tbody>
                    </table>
                )}
            </div>
        </div>
    );
}

// Component for RxNav drug interactions with search
function DrugInteractions() {
    const [interactions, setInteractions] = useState([]);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);
    const [drugName, setDrugName] = useState('');
    const [rxcui, setRxcui] = useState('34124'); // default

    const fetchInteractions = () => {
        if (!rxcui) return;
        setLoading(true);
        setError(null);

        fetch(`/api/v1/rxnav/interaction?rxcui=${rxcui}`)
            .then(response => response.json())
            .then(data => {
                const list = data.fullInteractionTypeGroup?.[0]?.fullInteractionType || [];
                setInteractions(list);
                setLoading(false);
            })
            .catch(err => {
                console.error('Error fetching interactions:', err);
                setError('Failed to load drug interactions.');
                setLoading(false);
            });
    };

    return (
        <div className="container mx-auto p-4 mt-6">
            <h2 className="text-2xl mb-4 font-semibold">Drug Interactions (RxNav API)</h2>

            <div className="mb-4 flex space-x-2">
                <input
                    type="text"
                    placeholder="Enter RxCUI or drug name"
                    value={rxcui}
                    onChange={e => setRxcui(e.target.value)}
                    className="p-2 border rounded flex-1"
                />
                <button
                    onClick={fetchInteractions}
                    className="bg-blue-500 text-white px-4 py-2 rounded"
                >
                    Search
                </button>
            </div>

            {loading && <p className="p-4">Loading drug interactions...</p>}
            {error && <p className="p-4 text-red-500">{error}</p>}

            {!loading && !error && (
                <div className="overflow-x-auto">
                    {interactions.length === 0 ? (
                        <p>No interactions found.</p>
                    ) : (
                        <table className="w-full border">
                            <thead>
                                <tr className="bg-gray-200">
                                    <th className="p-2 border">Drug Name</th>
                                    <th className="p-2 border">Severity</th>
                                    <th className="p-2 border">Description</th>
                                </tr>
                            </thead>
                            <tbody>
                                {interactions.map((item, index) => (
                                    <tr key={index} className="hover:bg-gray-100">
                                        <td className="p-2 border">{item.minConcept?.[0]?.name || 'N/A'}</td>
                                        <td className="p-2 border">{item.interactionPair?.[0]?.severity || 'N/A'}</td>
                                        <td className="p-2 border">{item.interactionPair?.[0]?.description || 'N/A'}</td>
                                    </tr>
                                ))}
                            </tbody>
                        </table>
                    )}
                </div>
            )}
        </div>
    );
}

// Main App component
function App() {
    return (
        <>
            <PrescriptionList />
            <DrugInteractions />
        </>
    );
}

// Render to DOM
ReactDOM.render(<App />, document.getElementById('root'));
