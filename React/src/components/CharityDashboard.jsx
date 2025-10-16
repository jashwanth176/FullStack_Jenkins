import React, { useEffect, useState } from 'react';
import axios from 'axios';
import './style.css';
import config from './config.js';

const CharityDashboard = () => {
  const [charities, setCharities] = useState([]);
  const [form, setForm] = useState({ id: '', name: '', description: '', goalAmount: '', contactEmail: '' });
  const [donation, setDonation] = useState({ charityId: '', donorName: '', donorEmail: '', amount: '' });
  const [message, setMessage] = useState('');
  const [editId, setEditId] = useState(null);

  const baseUrl = `${config.url}/api/charities`;

  const load = async () => {
    try {
      const res = await axios.get(baseUrl);
      setCharities(res.data);
    } catch {
      setMessage('Failed to load charities');
    }
  };

  useEffect(() => {
    (async () => {
      try {
        const res = await axios.get(baseUrl);
        setCharities(res.data);
      } catch {
        setMessage('Failed to load charities');
      }
    })();
  }, [baseUrl]);

  const onChange = (e) => setForm({ ...form, [e.target.name]: e.target.value });
  const onChangeDonation = (e) => setDonation({ ...donation, [e.target.name]: e.target.value });

  const resetForm = () => { setForm({ id: '', name: '', description: '', goalAmount: '', contactEmail: '' }); setEditId(null); };

  const save = async () => {
    try {
      const payload = { ...form, goalAmount: parseFloat(form.goalAmount || '0') };
      if (editId) {
        await axios.put(`${baseUrl}/${editId}`, payload);
        setMessage('Charity updated');
      } else {
        await axios.post(baseUrl, payload);
        setMessage('Charity created');
      }
      resetForm();
      load();
  } catch { setMessage('Save failed'); }
  };

  const edit = (c) => {
    setForm({ id: c.id, name: c.name, description: c.description, goalAmount: c.goalAmount, contactEmail: c.contactEmail });
    setEditId(c.id);
  };

  const remove = async (id) => {
  try { await axios.delete(`${baseUrl}/${id}`); setMessage('Deleted'); load(); } catch { setMessage('Delete failed'); }
  };

  const donate = async () => {
    try {
      const { charityId, donorName, donorEmail, amount } = donation;
      await axios.post(`${baseUrl}/${charityId}/donations`, { donorName, donorEmail, amount: parseFloat(amount || '0') });
      setMessage('Thanks for donating!');
      setDonation({ charityId: '', donorName: '', donorEmail: '', amount: '' });
      load();
  } catch { setMessage('Donation failed'); }
  };

  return (
    <div className="student-container">
      {message && (
        <div className={`message-banner ${message.toLowerCase().includes('fail') ? 'error' : 'success'}`}>
          {message}
        </div>
      )}

      <h2>Charity Fund Collection</h2>

      <div>
        <h3>{editId ? 'Edit Charity' : 'Add Charity'}</h3>
        <div className="form-grid">
          <input type="text" name="name" placeholder="Name" value={form.name} onChange={onChange} />
          <input type="text" name="description" placeholder="Description" value={form.description} onChange={onChange} />
          <input type="number" step="0.01" name="goalAmount" placeholder="Goal Amount" value={form.goalAmount} onChange={onChange} />
          <input type="email" name="contactEmail" placeholder="Contact Email" value={form.contactEmail} onChange={onChange} />
        </div>
        <div className="btn-group">
          <button className="btn-blue" onClick={save}>{editId ? 'Update' : 'Create'}</button>
          {editId && <button className="btn-gray" onClick={resetForm}>Cancel</button>}
        </div>
      </div>

      <div>
        <h3>Make a Donation</h3>
        <div className="form-grid">
          <input type="number" name="charityId" placeholder="Charity ID" value={donation.charityId} onChange={onChangeDonation} />
          <input type="text" name="donorName" placeholder="Your Name" value={donation.donorName} onChange={onChangeDonation} />
          <input type="email" name="donorEmail" placeholder="Your Email" value={donation.donorEmail} onChange={onChangeDonation} />
          <input type="number" step="0.01" name="amount" placeholder="Amount" value={donation.amount} onChange={onChangeDonation} />
        </div>
        <div className="btn-group">
          <button className="btn-green" onClick={donate}>Donate</button>
        </div>
      </div>

      <div>
        <h3>All Charities</h3>
        {charities.length === 0 ? (
          <p>No charities yet.</p>
        ) : (
          <div className="table-wrapper">
            <table>
              <thead>
                <tr>
                  <th>id</th>
                  <th>name</th>
                  <th>description</th>
                  <th>goalAmount</th>
                  <th>collectedAmount</th>
                  <th>contactEmail</th>
                  <th>Actions</th>
                </tr>
              </thead>
              <tbody>
                {charities.map(c => (
                  <tr key={c.id}>
                    <td>{c.id}</td>
                    <td>{c.name}</td>
                    <td>{c.description}</td>
                    <td>{c.goalAmount}</td>
                    <td>{c.collectedAmount}</td>
                    <td>{c.contactEmail}</td>
                    <td>
                      <div className="action-buttons">
                        <button className="btn-green" onClick={() => edit(c)}>Edit</button>
                        <button className="btn-red" onClick={() => remove(c.id)}>Delete</button>
                      </div>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        )}
      </div>
    </div>
  );
};

export default CharityDashboard;
