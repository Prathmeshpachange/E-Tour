using AutoMapper;
using SafarnamaApplication.DTOs;
using SafarnamaApplication.Models;
using SafarnamaApplication.Repositories;

namespace SafarnamaApplication.Services
{
    public class PassengerService : IPassengerService
    {
        private readonly IPassengerRepository _repo;
        private readonly IMapper _mapper;

        public PassengerService(IPassengerRepository repo, IMapper mapper)
        {
            _repo = repo;
            _mapper = mapper;
        }

        public IEnumerable<PassengerDTO> GetAllPassengers()
        {
            return _mapper.Map<IEnumerable<PassengerDTO>>(_repo.GetAll());
        }

        public PassengerDTO? GetPassengerById(int id)
        {
            var passenger = _repo.GetById(id);
            return passenger != null ? _mapper.Map<PassengerDTO>(passenger) : null;
        }

        public IEnumerable<PassengerDTO> GetPassengersByBookingId(int bookingId)
        {
            return _mapper.Map<IEnumerable<PassengerDTO>>(_repo.GetByBookingId(bookingId));
        }

        public void AddPassenger(PassengerDTO dto)
        {
            var passenger = _mapper.Map<Passenger>(dto);
            _repo.Add(passenger);
            _repo.Save();
        }

        public void UpdatePassenger(PassengerDTO dto)
        {
            var passenger = _mapper.Map<Passenger>(dto);
            _repo.Update(passenger);
            _repo.Save();
        }

        public void DeletePassenger(int id)
        {
            _repo.Delete(id);
            _repo.Save();
        }
    }
}
